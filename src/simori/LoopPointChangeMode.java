package simori;

import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * this is a class that is used within the performance mode, when the L4 button is pressed. 
 * We clear the buttons. when a column is clicked by pressing an x coordinate of the gridbutton column to 
 * be set to that column by highlighting it. this therefore allows the clockhand to loop earler than usual. 
 * @author Team G
 * 
 *
 */
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
        
   /**
    * method that is overriden from the Mode Interface to demonstrate that the 
    * matrix button has been processed in this specific mode. here, the method will highlight 
    * a specific column when the method is called. therefore an instance of the gridbutton will
    * be needed
    * @param button
    */
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
			/**
			 *method that is called when the OK button is pressed and will cause the okay button to flash
			 *quickly on and then off again, without any delay
			 *
			 */
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
        				SimoriOn.getInstanf all of the grid buce().getGui().OK.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffOK.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        				
        			}
        		}.run();
        		
        		
        		SimoriOn.getInstance().getGui().LCD.setText(null);
        		SimoriOn.getClockHand().setLoopPoint(loop);
        		SimoriOn.getInstance().setMode(new PerformanceMode());
        		SimoriOn.getInstance().getGui().turnOffFunctionButtons();
        	}

}

 

