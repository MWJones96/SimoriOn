package simori;

public class SimoriOn {

    private Mode mode;

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public boolean matrixButtonClicked() {
        this.mode.processMatrixButton();
        return false;
    }

}
