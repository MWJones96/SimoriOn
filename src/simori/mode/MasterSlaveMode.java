package simori.mode;

import simori.button.GridButton;
import simori.core.SimoriOn;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * this mode is activated after the R4 button is pressed.
 *  In this mode, all matrix buttons are cleared. The Simori-ON (the “master”) 
 *  probes for any other Simori-ON on the same local area network and selects the 
 *  first to respond (hereafter, the “slave”). A Simori-ON listens for network 13 
 *  connections on port 20160. The master then copies its entire configuration to 
 *  the slave and continues to Performance Mode.
 * 
 * @author team G
 *
 */

public class MasterSlaveMode implements Mode
{
	public MasterSlaveMode()
	{
		// Turn off Clockhand
		if (SimoriOn.getClockHand() != null) {
			SimoriOn.getClockHand().running.set(false);
		}

		if(!listenForMaster()) {
			listenForSlave();
		}


	}
	@Override
	public void processMatrixButton(GridButton button) {
		// TODO Auto-generated method stub

	}

	public void processOKButton(){

	}

	public boolean listenForMaster(){
		System.out.println("Listening for master on port 20160...");
		try (
				Socket clientSocket = new Socket("localhost", 20160);
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader((clientSocket.getInputStream()))
				);

		) {
			System.out.println("Connection to master established!");
			return true;
		} catch (IOException e){
			System.out.println("Connection refused.");
			return false;
		}
	}

	public boolean listenForSlave(){
		System.out.println("Listening for slave on port 20160...");
		try (
				ServerSocket serverSocket = new ServerSocket(20160);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
		) {
			System.out.println("Connection to slave established!");
			return true;
		} catch (IOException e){
			System.out.println("Connection refused.");
			return false;
		}
	}

}