package simori.mode;

import simori.core.Layer;
import simori.core.SimoriOn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        try {

            // Create new socket on port 20160
            Socket clientSocket = new Socket("localhost", 20160);

            // Create reader object
            BufferedReader in = new BufferedReader(
                    new InputStreamReader((clientSocket.getInputStream()))
            );
            System.out.println("Connection to master established!");

            String line;

            // Get array of all layers
            Layer[] layers = SimoriOn.getInstance().getLayers();

            // Layer number will be set to 0 for first line
            int layerNumber = -1;

            // Read line by line and set button array for each layer accordingly
            while ((line = in.readLine()) != null) {

                layerNumber += 1;
                String stringArray[] = line.split("");
                boolean[][] boolArray = new boolean[16][16];

                for (int i=0; i<256; i++){
                    if (stringArray[i].equals("0")){
                        boolArray[i % 16] [i / 16] = false;
                    }
                    else if (stringArray[i].equals("1")) {
                        boolArray[i % 16] [i / 16] = true;
                    }
                }

                if (layerNumber < 16) {
                    layers[layerNumber].setButtonArray(boolArray);
                }
            }
            // Close the BufferedReader and client socket when done
            in.close();
            clientSocket.close();

            // On success trigger the OK button to return to performance mode
            SimoriOn.getInstance().getMode().processOKButton();

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
