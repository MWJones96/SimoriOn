package simori;

public class VoiceChangeMode implements Mode {

    public VoiceChangeMode() 
    {	
    	if(SimoriOn.getClockHand() != null){
    		SimoriOn.getClockHand().running.set(false);
    	}
    }

    @Override
    public void processMatrixButton(GridButton button) 
    {
	// TODO Auto-generated method stub
        System.out.println("Matrix button processed in Voice Change Mode");
        
        // Highligh row and column of the button
        button.getGUI().highlightColumnAndRow(button.getCoordsX(), button.getCoordsY());
    }

}
