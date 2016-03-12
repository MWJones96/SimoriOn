package simori;

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
		SimoriOn.getInstance().getGui().highlightSegment(button.getCoordsX(), button.getCoordsY());
		// TODO Auto-generated method stub
		/*System.out
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
		SimoriOn.getInstance().getGui().writeToLCD(currentText);*/

	}

	/**
	 * once pressed, the file is saved as a .song file and goes back to the
	 * performance mode.
	 */
	public void processOKButton() {
	}

}
