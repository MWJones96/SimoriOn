package simori.mode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Team G
 */
public class Slave implements Runnable {

    AtomicBoolean success = new AtomicBoolean();

    public void run(){
        success.set(listenForMaster());
    }

    public boolean listenForMaster(){
        System.out.println("Listening for master on port 20160...");
        try (
                // Create new socket on port 20160
                Socket clientSocket = new Socket("localhost", 20160);

                // Create reader and writer objects
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

    public boolean getSuccess(){
        return success.get();
    }
}
