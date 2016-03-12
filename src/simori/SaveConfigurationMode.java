package simori;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
	char[][] alph = { { 'a', 'b', 'c', 'd', 'e' }, { 'f', 'g', 'h', 'i', 'j' },
			{ 'k', 'l', 'm', 'n', 'o' }, { 'p', 'q', 'r', 's', 't' },
			{ 'u', 'v', 'w', 'x', 'y' }, { 'z' } };

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
		}

	}

	/**
	 * when the button is pressed, append the letter to the LCD display
	 */
	@Override
	public void processMatrixButton(GridButton button) {
		// TODO Auto-generated method stub
		System.out
				.println("Matrix button processed in Save Configuration Mode");

		// Highlight row and column of the button
		button.getGUI().highlightColumnAndRow(button.getCoordsX(),
				button.getCoordsY());

		int x = button.getCoordsX();
		int y = button.getCoordsY();

		// Last grid button is "backspace" - deletes text
		if (x == 15 && y == 0) {
			if (!currentText.equals("")) {
				currentText = currentText
						.substring(0, currentText.length() - 1);
			}
		}

		// Last column selects letter in previous 3x3 square
		else if (x == 15 && y != 0) {
			currentText += alph[(15 - y) / 3][4] + "";
		}

		// Last row selects 'z'
		else if (y == 0 && x > 2) {
			currentText += alph[5][0] + "";
		}

		// Normal case - use index of array to determine selected letter
		else {
			currentText += alph[(15 - y) / 3][x / 3] + "";
		}
		SimoriOn.getInstance().getGui().writeToLCD(currentText);

	}

	/**
	 * once pressed, the file is saved as a .song file and goes back to the
	 * performance mode.
	 */
	public void processOKButton() {

		try {

			// Create new PrintWriter to write to selected filename
			PrintWriter writer = new PrintWriter(currentText + ".song", "UTF-8");

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
