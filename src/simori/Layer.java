package simori;

/**Class that represents a particular layer
 * of the Simori-ON device. One layer may be
 * sent to the GUI at any given time.
 * 
 * @author team G
 * @date 07/02/2016
 */
public class Layer 
{
	//Array containing information about whether each button is on or off
	private boolean[] buttonArray = new boolean[16 * 16];
	
	/**
	 * method that returns on/off the current layer on the GUI 
	 * (i.e. L1/L2/L3/L4) and  
	 * @return buttonArray is the x coordinate for each box
	 */
	public boolean[] getLayer()
	{
		return buttonArray;
	}
	
	/**
	 * method that will set the Layer. for the Simori - on, there 
	 * are 16 layers, with 256 buttons on the grid. 
	 * @param array
	 */
	public void setLayer(boolean[] array)
	{
		if(array.length != 16 * 16)
			throw new IllegalArgumentException("Array is not the appropriate size.");
		buttonArray = array;
	}

}
