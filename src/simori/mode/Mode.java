package simori.mode;

import simori.button.GridButton;

/**
 * Interface that contains one method. this is a basic template that buttons
 * that need some form of functionality should have allows for some
 * functionality of a button on the grid.
 * 
 * @author team G
 * 
 * @Verson 1.2;
 * @date 10/02/16
 */
public interface Mode {

	/**
	 * method that allows any button to have some form of functionality by
	 * overriding the method for each button
	 * 
	 * @param button
	 *            is an instance of the GridButton class
	 */
	public abstract void processMatrixButton(GridButton button);

	public void processOKButton();
}
