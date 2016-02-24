package simori;

import java.awt.Image;

import javax.swing.ImageIcon;

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
			
			SimoriOn.getInstance().getGui().writeToLCD("Loop point: " + Integer.toString(loop));
			System.out.println("Set Loop Point to " + loop);
		}

	}

        	public void processOKButton(){
                // Once OK button pressed in LoopPoint Mode (L4) go back to performance mode with set LoopPoint   
        		new Runnable(){
        			public void run()
        			{
        				System.out.println("Ohhaimark");
        				SimoriOn.getInstance().getGui().OK.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOnOK.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        				try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				SimoriOn.getInstance().getGui().OK.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffOK.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        				
        			}
        		}.run();
        		
        		
        		SimoriOn.getInstance().getGui().LCD.setText(null);
        		SimoriOn.getClockHand().setLoopPoint(loop);
        		SimoriOn.getInstance().setMode(new PerformanceMode());
        		SimoriOn.getInstance().getGui().turnOffFunctionButtons();
        	}

}

 

