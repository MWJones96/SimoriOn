package simori;

import java.awt.Image;

import javax.swing.ImageIcon;

public class LoopSpeedChangeMode implements Mode
{

	private int speed;

	public LoopSpeedChangeMode()
	{
		if (SimoriOn.getClockHand() != null) {
			SimoriOn.getClockHand().running.set(false);
		}
	}

	@Override
	public void processMatrixButton(GridButton button) {

		System.out.println("Matrix button processed in Loop Speed Change Mode");

		// Highlight row and column of the button
		button.getGUI().highlightColumnAndRow(button.getCoordsX(), button.getCoordsY());

		// Set speed variable
		if (button.getCoordsX() * button.getCoordsY() <= 160) {
			this.speed = button.getCoordsX() * button.getCoordsY();
		} else {
			this.speed = 160;
		}

		// Write out speed onto the LCD
		SimoriOn.getInstance().getGui().writeToLCD("Loop Speed: " + Integer.toString(this.speed));

		System.out.println("BPM Selected: " + this.speed);
		
	}

	public void processOKButton()
	{
		System.out.println("SET BPM To " + this.speed);

		SimoriOn.getInstance().getGui().LCD.setText(null);

		SimoriOn.getInstance().setBPM(this.speed);
		SimoriOn.getInstance().setMode(new PerformanceMode());
		SimoriOn.getInstance().getGui().turnOffFunctionButtons();
	}

}
