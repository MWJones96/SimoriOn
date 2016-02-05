package simori;

public class VoiceChangeMode implements Mode {

    private SimoriOn simori;

    public VoiceChangeMode(SimoriOn simori) {
        this.simori = simori;
    }

    @Override
    public void processMatrixButton() {
	// TODO Auto-generated method stub
        System.out.println("Matrix button processed in Voice Change Mode");
    }

}
