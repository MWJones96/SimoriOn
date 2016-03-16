package simori.mode;

import simori.button.GridButton;
import simori.core.SimoriOn;

/**
 * this is a class that is used when the R1 button is clicked on. when a matrix
 * button is pressed, the row of the matrix button will be selected. this is
 * used to choose a layer between 1-16 (or 0-15 ). this value will then be shown
 * on the LCD display. once the OK button is pressed the Simori on sets the
 * current layer to the chosen layer number and then continues in performance
 * mode. Layer Mode implemented when R1 clicked
 * 
 * @author Team G
 * 
 */
public class LayerChangeMode implements Mode {
	// Layer corresponding to y coordinate of button clicked
	private int Layer;

	/**
	 * constructor that is used to set the default values of the layermode
	 */
	public LayerChangeMode() {
		if (SimoriOn.getClockHand() != null) {
			SimoriOn.getClockHand().running.set(false);
		}
		SimoriOn.getInstance().getGui().writeToLCD("Layer: ");
	}

	/**
	 * overriding method that is used to see what button is pressed.
	 */
	@Override
	public void processMatrixButton(GridButton button) {
		Layer = button.getCoordsY();

		// Ensure all buttons are turned off
		button.getGUI().turnOffGridButtons();
		// Highlights one row based on y coordinate of button clicked
		button.getGUI().highlightOneRow(Layer);
		// Display on LCD the layer clicked based on y coordinate of button
		SimoriOn.getInstance().getGui()
				.writeToLCD("Layer: " + Integer.toString(Layer + 1));

	}

	/**
	 * method to perform actions when the OK button is pressed.
	 */
	public void processOKButton() {
		// Set layer to corresponding y coordinate of button clicked
		SimoriOn.getInstance().setCurrentLayer(Layer);
		
        // Resume performance mode
		SimoriOn.getInstance().setMode(new PerformanceMode());
		
		// Clear function button clicked
		SimoriOn.getInstance().getGui().turnOffFunctionButtons();
	}

}
