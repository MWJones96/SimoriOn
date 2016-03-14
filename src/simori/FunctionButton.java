package simori;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * this method is used as part of the GUI to return the buttons as toggle
 * buttons rather than JButtons. we have used .png files to simulate the left
 * hand L1-4 an R1-4 buttons as well for the on-off buttons that are associated
 * with the state of the button for example if L1 is clicked then L1 will turn
 * orange and when clicked again will be turned off and go back to the default
 * colour, grey.
 * 
 * @author team G
 * 
 */
public class FunctionButton extends JButton {
	private static final long serialVersionUID = 1L;

	protected ImageIcon onState;
	protected ImageIcon offState;

	// Off by default
	protected boolean state = false;

	/**
	 * constructor that will take the default state of the functional button
	 * that is - before the button has been clicked, give it the default state.
	 */
	public FunctionButton() {
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}

	/**
	 * set the icon to on, which is done by changing the colours from grey to
	 * orange.
	 */
	public void turnOn() {
		state = true;
		this.setIcon(onState);
	}

	/**
	 * set the icon to off, which is done by changing the colours from orange to
	 * grey
	 */
	public void turnOff() {
		state = false;
		this.setIcon(offState);
	}

	/**
	 * method that will get the state of the button that has either been pressed
	 * or not.
	 * 
	 * @return state as a boolean either true or false.
	 */
	public boolean getState() {
		return this.state;
	}
}