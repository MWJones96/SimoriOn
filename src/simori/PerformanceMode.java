package simori;

/**
 * Pressing a matrix button causes it to be set and illuminated in orange. Pressing
 * this button again, causes it to be cleared and not illuminated. The corresponding changes
 * are made to the current layer.
 * 
 * @author team G
 * @Verson 1.2;
 * @date 4/02/16
 * 
 */
public class PerformanceMode implements Mode {

	/**
	 * no argument constructor. initially if the performance mode button
	 * pressed, all grid buttons should be cleared. then start performance mode
	 * within its own thread. at the same time, it should also create a new
	 * clock hand that will move now in performance mode.
	 */
	public PerformanceMode() {
		if (SimoriOn.getClockHand() == null) {
			SimoriOn.setClockHand(new ClockHand());
			(new Thread(SimoriOn.getClockHand())).start();
		} else {
			(new Thread(SimoriOn.getClockHand())).start();
		}
	}

	/**
	 * overriding method from the Mode class that prints out the coordinate of
	 * the button clicked in the grid when in the ON mode. if clicked once, the
	 * button will be selected, and if clicked again, then it will be
	 * de-selected.
	 */
	@Override
	public void processMatrixButton(GridButton button) {
		// TODO Auto-generated method stub
		System.out.println("Matrix button processed in Performance Mode");
		System.out.println("Button clicked; co-ords: " + button.getCoordsX()
				+ ", " + button.getCoordsY());
		if (!button.getState()) {
			button.setToOnState();
			GridButton.addButtonsSelected(button);
			SimoriOn.getInstance()
					.getCurrentLayer()
					.setButtonState(button.getCoordsX(), button.getCoordsY(),
							true);
		} else {
			button.setToOffState();
			GridButton.removeButtonsSelected(button);
			SimoriOn.getInstance()
					.getCurrentLayer()
					.setButtonState(button.getCoordsX(), button.getCoordsY(),
							false);

		}
	}

	/**
	 * method that will reset when the OK button is pressed.
	 */
	public void processOKButton() {

	}

}
