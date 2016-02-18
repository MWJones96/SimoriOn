package simori;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ClockHand thread class - the object is created when the ON/OFF button is
 * clicked while in ON/OFF mode. Highlights the columns in sequence and sleeps
 * for a fixed amount of time between columns.
 * 
 * @author Team G
 * @date 09/02/2016.
 */

public class ClockHand implements Runnable {

	private GUI gui;
	private int loopPoint;
	private int velocity;

	public AtomicBoolean running = new AtomicBoolean();

	/**
	 * Constructor that is used to set the default constructor for which the
	 * clockhand will run on.
	 * 
	 * @param gui
	 *            - this is the GUI interface that the clockhand will use.
	 */

	public ClockHand(GUI gui) {
		this.gui = gui;
		this.loopPoint = 16;
		this.velocity = 64;
	}

	/**
	 * method will run the clockhand within its own thread. it causes the
	 * clockhand to move from the left hand side of the grid to the right hand
	 * side of the grid. whilst in ON mode, it should highlight the appropriate
	 * column as the clockhand oscillates.
	 * 
	 * @return none - method is void and therefore nothing returned.
	 */
	public void run() {
		System.out.println("Clockhand thread started");
		running.set(true);

		while (running.get()) {

			for (int i = 0; i < loopPoint; i++) {
				gui.highlightColumn(i);

				// Iterate through all selected buttons
				for (int j = 0; j < GridButton.getButtonsSelected().size(); j++) {
					GridButton button = GridButton.getButtonsSelected().get(j);

					// If selected button is in the current column, play sound
					if (button.getCoordsX() == i) {
						// Sound test
						(new Thread(new Sounds(50 - button.getCoordsY(), velocity)))
								.start();

					}
				}

				if (!running.get()) {
					for (GridButton button : gui.buttons) {
						button.setToOffState();
					}
					break;
				}

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
// Dennis