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
			System.out.println("Set velocity to " + velocity);
		}

		// Listener for OK button (maybe needs to be moved somewhere else)
		SimoriOn.getInstance().getGui().OK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (SimoriOn.getInstance().getMode() instanceof VelocityChangeMode) {
					SimoriOn.getClockHand().setVelocity(velocity);
					SimoriOn.getInstance().setMode(new PerformanceMode());
					SimoriOn.getInstance().getGui().turnOffFunctionButtons();

				}
			}
		});

	}

}
