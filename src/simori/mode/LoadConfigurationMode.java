package simori.mode;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import simori.button.GridButton;
import simori.core.Layer;
import simori.core.SimoriOn;


/**
 * this mode is activated when the R3 button is pressed. Pressing a matrix button causes it and all those
 * in the same vertical/horizontal line (only) to be set. The
 * coordinates of the pressed matrix button are somehow used to edit the
 * individual characters of a filename which will be given a “.song” extension. 
 * Pressing the “OK” button causes the Simori-ON to load its entire configuration 
 * from the named file and then to continue to Performance Mode.
 * 
 * @author team G
 *
 */
public class LoadConfigurationMode implements Mode {
        final char[] ALPH_UPPER = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
							   'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	final char[] ALPH_LOWER = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
							   'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	String currentText = "";
            
        /**
	 * constructor that is used to set the default state of the LoadConfigurationmode.
	 */
	public LoadConfigurationMode() {
		// Turn Off Clockhand
		{
			if (SimoriOn.getClockHand() != null) {
				SimoriOn.getClockHand().running.set(false);
			}
			
			SimoriOn.getInstance().getGui().writeToLCD("Song to load: ");
		}

	}

	/**
	 * when the button is pressed, append the letter to the LCD display
	 */
	@Override
	public void processMatrixButton(GridButton button) {
		SimoriOn.getInstance().getGui().turnOffGridButtons();
		SimoriOn.getInstance().getGui().highlightSegment(button.getCoordsX(), button.getCoordsY());
		
		if(button.getCoordsY() <= 15 && button.getCoordsY() >= 12)
			currentText = currentText.concat(Character.toString(ALPH_UPPER[button.getCoordsX()]));
		else if(button.getCoordsY() < 12 && button.getCoordsY() >= 8)
		{
			try
			{
				currentText = currentText.concat(Character.toString(ALPH_UPPER[button.getCoordsX() + 16]));
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				//Dealing with the leftover segments
				//Adds a space to the song name
				currentText = currentText.concat(" ");
			}
		}
		
		else if(button.getCoordsY() < 8 && button.getCoordsY() >= 4)
			currentText = currentText.concat(Character.toString(ALPH_LOWER[button.getCoordsX()]));
		else if(button.getCoordsY() < 4 && button.getCoordsY() >= 0)
		{
			try
			{
				currentText = currentText.concat(Character.toString(ALPH_LOWER[button.getCoordsX() + 16]));
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				//Dealing with the leftover segments
				//Deletes the previous character
				currentText = currentText.substring(0, currentText.length() - 1);
			}
		}
		
		SimoriOn.getInstance().getGui().LCD.setText("Song to load: " + currentText);
		
		
	}
	
        /**
	 * Once pressed, the file corresponding to the inputted text is loaded from the song folder and shown back on
	 * performance mode.
	 */
	public void processOKButton() {
            BufferedReader reader = null;   
            try {
                // Read file with inputted text
                File file = new File("./songs/" + currentText + ".song");
                reader = new BufferedReader(new FileReader(file));

                String line;

                // Get array of all layers
				Layer[] layers = SimoriOn.getInstance().getLayers();

				// Layer number will be set to 0 for first line in the file
				int layerNumber = -1;

				// Read line by line and set button array for each layer accordingly
                while ((line = reader.readLine()) != null) {

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
					System.out.println();

					if (layerNumber < 16) {
						layers[layerNumber].setButtonArray(boolArray);
					}
                }
				// Close the BufferedReader when done
				reader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

			SimoriOn.getInstance().getGui().LCD.setText("Now playing " + currentText + ".song");
			SimoriOn.getInstance().setMode(new PerformanceMode());
			SimoriOn.getInstance().getGui().turnOffFunctionButtons();
	}

}