package simori;

/**
 * this mode is activated when the R3 button is pressed. Pressing a matrix button causes it and all those
 * in the same vertical/horizontal line (only) to be set. The
 * coordinates of the pressed matrix button are somehow used to edit the
 * individual characters of a filename which will be given a “.song” extension. 
 * Pressing the “OK” button causes the Simori-ON to load its entire configuration 
 * from the named file and then to continue to Performance Mode.
 * 
 * @author team G
 *
 */
public class LoadConfigurationMode implements Mode {

	@Override
	public void processMatrixButton(GridButton button) {
		// TODO Auto-generated method stub

	}

	public void processOKButton() {

	}

}