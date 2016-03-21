package simori.mode;

import simori.core.Layer;
import simori.core.SimoriOn;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Team G
 */
public class Master implements Runnable {

    AtomicBoolean success = new AtomicBoolean();
    public void run() {
        success.set(listenForSlave());
    }

    public boolean listenForSlave(){
        System.out.println("Listening for slave on port 20160...");
        try (
                // Create new socket on port 20160
                ServerSocket serverSocket = new ServerSocket(20160);
                Socket clientSocket = serverSocket.accept();

                // Create writer object
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)

        ) {
            System.out.println("Connection to slave established!");

            // Get array of all layers
            Layer[] layers = SimoriOn.getInstance().getLayers();

            // For each layer iterate through the button array and send
            // its binary values to the slave
            for (int i=0; i< layers.length; i++){

                for (int j=0; j< 256; j++){
                    out.print((layers[i].getButtonArray()[j % 16] [j / 16]) ? 1:0);
                }
                // Each line represents a separate layer
                out.println();
            }

            // Close the PrintWriter and sockets when done
            out.close();
            serverSocket.close();
            clientSocket.close();

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
