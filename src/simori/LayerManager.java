package simori;

/**Class that facilitates communication between 
 * the GUI and the Layer classes.
 * 
 * @author 8744
 * @date 07/02/2016
 */
public class LayerManager 
{
	private Layer[] layers = new Layer[16];
	
	/**Selects an index between 1 and 16
	 * as described in the specification.
	 * 
	 * @param index - the layer to access
	 * @return layers[index] - the appropriate layer
	 */
	public Layer getLayer(int index)
	{
		if(index < 1 || index > 16)
			throw new IllegalArgumentException("Index given is out of range.");
		return layers[index];
	}
}
