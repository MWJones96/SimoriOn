package simori;

/**
 * an interface that contains one method. 
 * this is a basic template that buttons that need some
 * form of functionality should have
 * allows for some functionality of a button on the grid.
 * @author team G
 *
 */
public interface Mode {

	/**
	 * method that allows any button to have some form of 
	 * functionality by overriding the method for each button
	 * @param button is an instance of the GridButton class
	 */
    public abstract void processMatrixButton(GridButton button);
}
