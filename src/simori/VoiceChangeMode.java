package simori;

public class VoiceChangeMode implements Mode {

    public VoiceChangeMode() {
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
