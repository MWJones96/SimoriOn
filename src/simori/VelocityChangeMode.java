package simori;

/**
 * this is a class that is used under Performance mode when the L2 button is
 * clicked. In this mode Pressing a matrix button causes it and all those in the
 * same vertical/horizontal line (only) to be set. See Figure 5. The coordinates
 * of the pressed matrix button are used to choose a note velocity
 * 
 * @author Team G
 * 
 */
public class VelocityChangeMode implements Mode {
	private int velocity;

	/**
	 * constructor that creates the clock hand in a non-runnable state.
	 */
	public VelocityChangeMode() {
		if (SimoriOn.getClockHand() != null) {
			SimoriOn.getClockHand().running.set(false);
		}
	}

	/**
	 * method that checks firstly that the button has been pressed and the
	 * coordinates of the button that has been clicked. once it has, then based
	 * upon the click, the velocity will be chosen within the given range.
	 * 
	 * @param button
	 */
	@Override
	public void processMatrixButton(GridButton button) {
		// TODO Auto-generated method stub
		System.out.println("Matrix button processed in Velocity Change Mode");

		// Highlight row and column of the button
		button.getGUI().highlightColumnAndRow(button.getCoordsX(), button.getCoordsY());

		// Set the velocity to x*y based on the button pressed
		if (SimoriOn.getClockHand() != null) {

			// Every two squares corresponds to a distinct velocity value, from
			// 0 to 127
			velocity = (int) ((button.getCoordsY() * 16 + button.getCoordsX()) / 2);

			SimoriOn.getInstance().getGui().writeToLCD("Velocity: " + Integer.toString(velocity));
			System.out.println("Set velocity to " + velocity);
		}

	}

	/**
	 * method that is used when the OK button is pressed. here the LCD is set
	 * back to NULL and the Simori ON goes back to the performance mode
	 */
	public void processOKButton() {
		SimoriOn.getInstance().getGui().LCD.setText(null);
		SimoriOn.getClockHand().setVelocity(velocity);
		SimoriOn.getInstance().setMode(new PerformanceMode());
		SimoriOn.getInstance().getGui().turnOffFunctionButtons();
	}

}
