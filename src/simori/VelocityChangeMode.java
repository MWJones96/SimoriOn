package simori;

public class VelocityChangeMode implements Mode
{
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

	}

}
