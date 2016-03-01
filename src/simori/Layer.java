package simori;

/**
 * Class that represents a particular layer of the Simori-ON device. One layer
 * may be sent to the GUI at any given time.
 * 
 * @author team G
 * @date 07/02/2016
 */
public class Layer {
	// Holds instrument selected
	private int currentInstrument = 1;


	// Array containing information about whether each button is on or off
	private boolean[][] buttonArray = new boolean[16][16];

	public Layer(){
		System.out.println("Creating layer");
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				this.buttonArray[i][j] = false;
			}
		}
	}

	public boolean getButtonState(int x, int y){
		return this.buttonArray[x][y];
	}

	public void setButtonState(int x, int y, boolean state){
		this.buttonArray[x][y] = state;
	}

	public boolean[] getButtonsColumn(int x){
		System.out.println(x);
		System.out.println(this.buttonArray[x].length);
		return this.buttonArray[x];
	}

	/**
	 * method that returns on/off the current layer on the GUI (i.e.
	 * L1/L2/L3/L4) and
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
	 * @return int
	 */
	public int getCurrentInstrument(){
		return this.currentInstrument;
	}

	public void setInstrument(int instrument){
		this.currentInstrument = instrument;
	}

	public void turnOffAll(){
		for(int i=0; i<this.buttonArray.length;i++){
			for(int j=0; j<this.buttonArray[i].length;j++){
				this.buttonArray[i][j] = false;
			}
		}
	}

}
