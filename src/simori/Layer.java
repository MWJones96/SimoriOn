package simori;

/**Class that represents a particular layer
 * of the Simori-ON device. One layer may be
 * sent to the GUI at any given time.
 * 
 * @author 8744
 * @date 07/02/2016
 */
public class Layer 
{
	//Array containing information about whether each button is on or off
	private boolean[] buttonArray = new boolean[16 * 16];
	
	public boolean[] getLayer()
	{
		return buttonArray;
	}
	
	public void setLayer(boolean[] array)
	{
		if(array.length != 16 * 16)
			throw new IllegalArgumentException("Array is not the appropriate size.");
		buttonArray = array;
	}

}
