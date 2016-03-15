package simori.mode;

import simori.button.GridButton;

/**
 * this mode is actiavted after teh R4 button is pressed. 
 *  In this mode, all matrix buttons are cleared. The Simori-ON (the “master”) 
 *  probes for any other Simori-ON on the same local area network and selects the 
 *  first to respond (hereafter, the “slave”). A Simori-ON listens for network 13 
 *  connections on port 20160. The master then copies its entire configuration to 
 *  the slave and continues to Performance Mode.
 * 
 * @author team G
 *
 */
public class MasterSlaveMode implements Mode {

	@Override
	public void processMatrixButton(GridButton button) {
		// TODO Auto-generated method stub

	}

	public void processOKButton() {

	}

}