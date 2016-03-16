package simori.mode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import simori.button.GridButton;
import simori.core.Layer;
import simori.core.SimoriOn;

/**
 * this is a class that is activated when the R2 button is clicked. this mode is
 * the Save Configuration mode. when a matrix button is pressed, is causes the
 * vertical and horizontal lines to be pressed of that matrix button. when
 * pressed, the matrix buttons are used to edit the individual characters of a
 * filename, which is up to the user and be given a .song extension when saved.
 * once the OK button is pressed, the configuration is saved and go back to
 * performance mode.
 *
 * @author team G
 *
 */
public class SaveConfigurationMode implements Mode {
	final char[] ALPH_UPPER = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
							   'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	final char[] ALPH_LOWER = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
							   'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	String currentText = "";

	/**
	 * constructor that is used to set the default state of the saveConfigmode.
	 */
	public SaveConfigurationMode() {
		// Turn Off Clockhand
		{
			if (SimoriOn.getClockHand() != null) {
				SimoriOn.getClockHand().running.set(false);
			}
			
			SimoriOn.getInstance().getGui().writeToLCD("File to save: ");
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
				if (!currentText.equals("")) {
					currentText = currentText.substring(0, currentText.length() - 1);
				}
			}
		}
		
		SimoriOn.getInstance().getGui().writeToLCD("File to save: " + currentText);
		
		
	}

	/**
	 * once pressed, the file is saved as a .song file and goes back to the
	 * performance mode.
	 */
	public void processOKButton() {
		try {
			// Create new PrintWriter to write to selected filename
			PrintWriter writer = new PrintWriter("./songs/" + currentText + ".song", "UTF-8");

			// Get array of all layers
			Layer[] layers = SimoriOn.getInstance().getLayers();

			// For each layer iterate through the button array and print
			// its binary values to the file
			for (int i=0; i< layers.length; i++){

				for (int j=0; j< 256; j++){
					writer.print((layers[i].getButtonArray()[j % 16] [j / 16]) ? 1:0);
				}
				// Each line in the file represents a separate layer
				writer.println();
			}

			// Close the PrintWriter when done
			writer.close();

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		System.out.println("Saved configuration to " + currentText + ".song");

		SimoriOn.getInstance().getGui().LCD.setText(null);
		SimoriOn.getInstance().setMode(new PerformanceMode());
		SimoriOn.getInstance().getGui().turnOffFunctionButtons();
	}

}