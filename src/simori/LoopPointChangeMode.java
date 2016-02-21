package simori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoopPointChangeMode implements Mode
{
        private int loop;
        
        public LoopPointChangeMode()
        // Turn Off Clockhand
	{
		if (SimoriOn.getClockHand() != null) {
			SimoriOn.getClockHand().running.set(false);
		}
	}
        
	@Override
	public void processMatrixButton(GridButton button) {
		// TODO Auto-generated method stub
		System.out.println("Matrix button processed in Loop Point Mode");

		// Highlight designated column of the button
		button.getGUI().highlightOneColumn(button.getCoordsX());
                
                // Set the loop point to x coordinate where button is pressed
		if (SimoriOn.getClockHand() != null) {

			if (button.getCoordsX() <= 16) {
				loop = button.getCoordsX();

			} else {
				loop = 15;
			}
			
			SimoriOn.getInstance().getGui().writeToLCD(Integer.toString(loop));
			System.out.println("Set Loop Point to " + loop);
		}

	}

        	public void processOKButton(){
                // Once OK button pressed in LoopPoint Mode (L4) go back to performance mode with set LoopPoint    
		SimoriOn.getInstance().getGui().LCD.setText(null);

		SimoriOn.getClockHand().setLoopPoint(loop);
		SimoriOn.getInstance().setMode(new PerformanceMode());
		SimoriOn.getInstance().getGui().turnOffFunctionButtons();
	}

}

 

