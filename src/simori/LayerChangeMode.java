package simori;

/**
 * 
 * @author
 * 
 */
public class LayerChangeMode implements Mode
{

	public LayerChangeMode(){
		if (SimoriOn.getClockHand() != null) {
			SimoriOn.getClockHand().running.set(false);
		}
	}

	@Override
	public void processMatrixButton(GridButton button) {
		// TODO Auto-generated method stub
		
	}

	public void processOKButton(){
		SimoriOn.getInstance().setCurrentLayer(2);
		SimoriOn.getInstance().setMode(new PerformanceMode());
		SimoriOn.getInstance().getGui().turnOffFunctionButtons();
	}

}
