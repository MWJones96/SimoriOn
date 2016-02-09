package simori;

public class VoiceChangeMode implements Mode {

    public VoiceChangeMode() {
    }

    @Override
    public void processMatrixButton(Buttons button) {
	// TODO Auto-generated method stub
        System.out.println("Matrix button processed in Voice Change Mode");
        
        // Highligh row and column of the button
        for(int i=0; i<16; i++){
            button.getGUI().getButton(button.getCoordsX(), i).highlightOn();
            button.getGUI().getButton(i, button.getCoordsY()).highlightOn();
        }
    }

}
