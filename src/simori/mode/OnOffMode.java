package simori.mode;

import simori.SimoriOn;
import simori.button.GridButton;

/**
 *A Simori-ON may be switched on by pressing the “ON” button, after which it continues to
 *Performance Mode. Simori-ON may be switched off by pressing the “ON” button, after which it continues 
 *to On/Off Mode
 * 
 * @author team G
 * 
 * @Verson 1.0;
 * @date 11/02/16
 * 
 */
public class OnOffMode implements Mode {
	/**
	 * default constructor that is set by default to the "off" state. when
	 * button ON button clicked once, button state changes to ON. if clicked
	 * again, button state changes back to off.
	 */
	public OnOffMode() {
		if (SimoriOn.getClockHand() != null) {
			System.out.println("turn off clockhand");
			SimoriOn.getClockHand().running.set(false);
		}
	}

	/**
	 * overriding method from the Mode.java interface. as ON/OFF button is a
	 * button, it holds some functionality and will print to the console.
	 * information will print out when in "off" mode /default mode.
	 */
	@Override
	public void processMatrixButton(GridButton button) {
		System.out.println("Matrix button processed in OnOffMode");
		System.out.println("X: " + button.getCoordsX() + "; Y: "
				+ button.getCoordsY());
	}

	public void processOKButton() {

	}

}
