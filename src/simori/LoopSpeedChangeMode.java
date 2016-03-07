package simori;


/**
 * this is a class that is used within performance mode again when the L3 button is pressed. here
 * all the buttons are cleared as usual, when a button is clicked, the specific row and column will be selected. 
 * within this class, the range of speed that can be chosen can be within the boundaries of 0-160bpm
 * @author team G
 *
 */
public class LoopSpeedChangeMode implements Mode
{

	private int speed;
	
	public LoopSpeedChangeMode()
	{
		if (SimoriOn.getClockHand() != null) {
			SimoriOn.getClockHand().running.set(false);
		}
	}
	/**
	 * shows that the matrix button has been pressed in the correct mode so that way the loop 
	 * speed can be changed. if the speed is 0, then the instrument being played will be silent. 
	 * once the oK button is pressed, then it will go back to performance mode. 
	 * @param button
	 */
	@Override
	public void processMatrixButton(GridButton button) {

		System.out.println("Matrix button processed in Loop Speed Change Mode");

		// Highlight row and column of the button
		button.getGUI().highlightColumnAndRow(button.getCoordsX(), button.getCoordsY());

		// Set speed variable
		if (button.getCoordsX() * button.getCoordsY() <= 160) {
			this.speed = button.getCoordsX() * button.getCoordsY();
		} else {
			this.speed = 160;
		}

		// Write out speed onto the LCD
		SimoriOn.getInstance().getGui().writeToLCD("Loop Speed: " + Integer.toString(this.speed));

		System.out.println("BPM Selected: " + this.speed);
		
	}
	/**
	 * once the oK button is pressed, then it will go back to performance mode. 
	 */
	public void processOKButton()
	{
		System.out.println("SET BPM To " + this.speed);

		SimoriOn.getInstance().getGui().LCD.setText(null);
		SimoriOn.getClockHand().setLoopSpeed(this.speed);
		SimoriOn.getInstance().setMode(new PerformanceMode());
		SimoriOn.getInstance().getGui().turnOffFunctionButtons();
	}

}
