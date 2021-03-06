package simori.core;

/**
 * Class that represents a particular layer of the Simori-ON device. One layer
 * may be sent to the GUI at any given time.
 * 
 * @author team G
 * @date 07/02/2016
 */
public class Layer {
	// Holds instrument selected
	private int currentInstrument = 0;

	// Array containing information about whether each button is on or off
	private boolean[][] buttonArray = new boolean[16][16];

	public Layer() {
		System.out.println("Creating layer");
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				this.buttonArray[i][j] = false;
			}
		}
	}

	/**
	 * method that gets the state of a button based on its coordinates
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean getButtonState(int x, int y) {
		return this.buttonArray[x][y];
	}
	
	public int getLayerIndex()
	{
		for(int i = 0; i < 16; i++)
			if(this.equals(SimoriOn.getInstance().getLayers()[i]))
				return (i + 1);
		return 0;
	}

	/**
	 * method that sets the state of a particular button based on its
	 * coordinate.
	 * 
	 * @param x
	 * @param y
	 * @param state
	 */
	public void setButtonState(int x, int y, boolean state) {
		this.buttonArray[x][y] = state;
	}

	/**
	 * method that gets the column of a specific button bas on the x coordinate
	 * of the button
	 * 
	 * @param x
	 * @return
	 */
	public boolean[] getButtonsColumn(int x) {
		return this.buttonArray[x];
	}

	/**
	 * method that returns on/off the current layer on the GUI (i.e.
	 * L1/L2/L3/L4)
	 * 
	 * @return buttonArray is the x coordinate for each box
	 */
	public boolean[][] getButtonArray() {
		return buttonArray;
	}

	/**
	 * method that will set the Layer. for the Simori - on, there are 16 layers,
	 * with 256 buttons on the grid.
	 * 
	 * @param array
	 */
	public void setButtonArray(boolean[][] array) {
		if (array.length != 16 || array[0].length != 16)
			throw new IllegalArgumentException(
					"Array is not the appropriate size.");
		buttonArray = array;
	}

	/**
	 * Retrieve currently selected instrument number
	 * 
	 * @return int
	 */
	public int getCurrentInstrument() {
		return this.currentInstrument;
	}

	/**
	 * method that sets the instrument (which is an int number).
	 * 
	 * @param instrument
	 */
	public void setInstrument(int instrument) {
		this.currentInstrument = instrument;
	}

	/**
	 * method that turns off all buttons
	 */
	public void turnOffAll() {
		for (int i = 0; i < this.buttonArray.length; i++) {
			for (int j = 0; j < this.buttonArray[i].length; j++) {
				this.buttonArray[i][j] = false;
			}
		}
	}

}
