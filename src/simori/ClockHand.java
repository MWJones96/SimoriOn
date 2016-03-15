package simori;

import java.util.concurrent.atomic.AtomicBoolean;

import simori.button.GridButton;

/**
 * ClockHand thread class - the object is created when the ON/OFF button is
 * clicked while in ON/OFF mode. A “clock hand” continuously loops from left to
 * right at a set speed as far as a set loop point, playing the notes described
 * by each layer on its channel, using its voice with its velocity. Notes in
 * higher rows have higher pitch.
 * 
 * @author Team G
 * @date 09/02/2016.
 */

public class ClockHand implements Runnable {
	// The final point at which the clock hand will go before looping back
	private int loopPoint;

	// The speed at which the clock will loop (in beats per minute)
	private int loopSpeed;

	// The velocity of the note
	private int velocity;

	public AtomicBoolean running = new AtomicBoolean();

	/**
	 * Constructor that is used to set the default constructor for which the
	 * clock hand will run on.
	 * 
	 */

	public ClockHand() {
		this.loopPoint = 16;
		this.loopSpeed = 60;
		this.velocity = 32;
	}

	/**
	 * method will run the clock hand within its own thread. it causes the clock
	 * hand to move from the left hand side of the grid to the right hand side
	 * of the grid. while in ON mode, it should highlight the appropriate column
	 * as the clock hand oscillates.
	 * 
	 * @return none - method is void and therefore nothing returned.
	 */
	@Override
	public void run() {
		System.out.println("Clock hand thread started");
		running.set(true);

		while (running.get()) {
			// Cycle through all of the columns, then cycle through all the
			// layers, then all the notes in the column

			for (int i = 0; i < loopPoint; i++) {
				SimoriOn.getInstance().getGui().highlightClockColumn(i);

				for (int j = 0; j < SimoriOn.getInstance().getLayers().length; j++) {
					for (int k = 0; k < SimoriOn.getInstance().getLayer(j).getButtonsColumn(i).length; k++) {
						if (SimoriOn.getInstance().getLayer(j).getButtonsColumn(i)[k]) {
							System.out.println("Creating sound");
							// New test - use sound processor
							SimoriOn.getInstance().getSoundProcessor().playSound(35 + k,
									velocity * 2 /*
													 * Low velocities are too quiet
													 * to be heard
													 */, j);
						}
					}
				}

				if (!running.get())
					break;

				try {
					// Sleep for an appropriate amount
					// System.out.println(Math.round(60000 /
					// SimoriOn.getInstance().getLoopSpeed()));
					// 60000 ms (1min) / bpm (beats per minute) / 4 (4 steps per
					// beat)
					Thread.sleep(Math.round(60000 / loopSpeed / 4));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		// After the clock hand has been turned off, clear the matrix buttons
		for (GridButton button : SimoriOn.getInstance().getGui().buttons) {
			button.setToOffState();
		}

	}

	/**
	 * method that sets the velocity for the main clock hand
	 * 
	 * @param velocity
	 */
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	/**
	 * method that will get the velocity back after it has been set
	 * 
	 * @return
	 */
	public int getVelocity() {
		return velocity;
	}

	/**
	 * method that will reset the clock hand after a specific column is selected
	 * within the GUI.
	 * 
	 * @param loopPoint
	 */
	public void setLoopPoint(int loopPoint) {
		this.loopPoint = loopPoint;
	}

	/**
	 * method that will get the loop point value back
	 * 
	 * @return
	 */
	public int getLoopPoint() {
		return loopPoint;
	}

	/**
	 * method that is used to set the loop speed of the clock hand
	 * 
	 * @param loopSpeed
	 */
	public void setLoopSpeed(int loopSpeed) {
		this.loopSpeed = loopSpeed;
	}

	/**
	 * method that returns the loop speed after it has been set.
	 * 
	 * @return
	 */
	public int getLoopSpeed() {
		return loopSpeed;
	}
}