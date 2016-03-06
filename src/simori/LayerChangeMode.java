package simori;

/**
 * Layer Mode implemented when R1 clicked
 * @author Team G
 * 
 */
public class LayerChangeMode implements Mode
{       
        // Layer corresponding to y coordinate of button clicked
        private int Layer; 
    
	public LayerChangeMode(){
		if (SimoriOn.getClockHand() != null) {
			SimoriOn.getClockHand().running.set(false);
		}
                
	}

	@Override
	public void processMatrixButton(GridButton button) {
                Layer = button.getCoordsY();
                
                // Ensure all buttons are turned off 
                button.getGUI().turnOffGridButtons();
                //Highlights one row based on y coordinate of button clicked
                button.getGUI().highlightOneRow(Layer);
                //Display on LCD the layer clicked based on y coordinate of button
		SimoriOn.getInstance().getGui().writeToLCD("Layer: " + Integer.toString(Layer));
   
	}

	public void processOKButton(){
                // Set layer to corresponding y coordinate of button clicked 
		SimoriOn.getInstance().setCurrentLayer(Layer);
                // Resume performance mode
		SimoriOn.getInstance().setMode(new PerformanceMode());
                // Clear function button clicked
		SimoriOn.getInstance().getGui().turnOffFunctionButtons();
	}

}
