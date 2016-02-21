package simori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VelocityChangeMode implements Mode
{
	private int velocity;

	public VelocityChangeMode()
	{
		if (SimoriOn.getClockHand() != null) {
			SimoriOn.getClockHand().running.set(false);
		}
	}
	@Override
	public void processMatrixButton(GridButton button) {
		// TODO Auto-generated method stub
		System.out.println("Matrix button processed in Velocity Change Mode");

		// Highlight row and column of the button
		button.getGUI().highlightColumnAndRow(button.getCoordsX(),
				button.getCoordsY());


		// Set the velocity to x*y based on the button pressed
		if (SimoriOn.getClockHand() != null) {

			if (button.getCoordsX() * button.getCoordsY() <= 127) {
				velocity = button.getCoordsX() * button.getCoordsY();

			} else {
				velocity = 127;
			}
			
			SimoriOn.getInstance().getGui().writeToLCD(Integer.toString(velocity));
			System.out.println("Set velocity to " + velocity);
		}

	}

	public void processOKButton(){
		SimoriOn.getInstance().getGui().LCD.setText(null);

		SimoriOn.getClockHand().setVelocity(velocity);
		SimoriOn.getInstance().setMode(new PerformanceMode());
		SimoriOn.getInstance().getGui().turnOffFunctionButtons();
	}

}
