package simori.mode;

import simori.button.GridButton;
import simori.core.SimoriOn;

public class ShopBoyMode implements Mode
{
	public ShopBoyMode() 
	{
		if (SimoriOn.getClockHand() != null) {
			SimoriOn.getClockHand().running.set(false);
		}
		SimoriOn.getInstance().getGui().writeToLCD(null);
	}
	
	@Override
	public void processMatrixButton(GridButton button) 
	{
		
	}

	@Override
	public void processOKButton() 
	{
		SimoriOn.getInstance().setMode(new PerformanceMode());
	}

}