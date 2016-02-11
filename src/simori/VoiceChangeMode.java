package simori;

/**
 * class that has been created to allow the user to select the voice of the
 * performance mode.
 * @author Team G
 *
 */
public class VoiceChangeMode implements Mode {

	/**
	 * constructor that checks to see if the clockhand is in existance yet. 
	 * if it is not, then running will be set to false, and will not move. 
	 */
    public VoiceChangeMode() 
    {	
    	if(SimoriOn.getClockHand() != null){
    		SimoriOn.getClockHand().running.set(false);
    	}
    }
    /**
     * ovveriding method from the Mode class to enable functionality of the buttons. 
     */
    @Override
    public void processMatrixButton(GridButton button) 
    {

        System.out.println("Matrix button processed in Voice Change Mode");
        
        // Highligh row and column of the button
        button.getGUI().highlightColumnAndRow(button.getCoordsX(), button.getCoordsY());
    }

}
