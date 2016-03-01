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

	private int loopPoint;
	private int velocity;

	public AtomicBoolean running = new AtomicBoolean();

	/**
	 * Constructor that is used to set the default constructor for which the
	 * clockhand will run on.
	 *
	 */

	public ClockHand() {
		this.loopPoint = 16;
		this.velocity = 64;
	}
	
	/**
	 * method that sets the veolocity for the main clockhand
	 * @param velocity
	 */
	public void setVelocity(int velocity){
		this.velocity = velocity;
	}

	/**
	 * method that will get the velocity back after it has been set 
	 * @return
	 */
	public int getVelocity(){
		return velocity;
	}

	/**
	 * method that will reset the clockhand after a specific column
	 * is selected wihtin the GUI. 
	 * @param loopPoint
	 */
	public void setLoopPoint(int loopPoint){
		this.loopPoint = loopPoint;
	}

	/**
	 * method that will get the loop point value back 
	 * @return
	 */
	public int getLoopPoint() {
		return loopPoint;
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
				SimoriOn.getInstance().getGui().highlightClockColumn(i);

				// Iterate through all selected buttons
				/*for (int j = 0; j < GridButton.getButtonsSelected().size(); j++) {
					GridButton button = GridButton.getButtonsSelected().get(j);

					// If selected button is in the current column, play sound
					if (button.getCoordsX() == i) {
						// Sound test
						//(new Thread(new Sounds(50 - button.getCoordsY(), velocity))).start();
						// New test - use sound processor
						SimoriOn.getInstance().getSoundProcessor().playSound(35+button.getCoordsY(), velocity);

					}
				}*/
				for(int l=0; l<SimoriOn.getInstance().getLayers().length;l++) {
					for (int j = 0; j < SimoriOn.getInstance().getLayer(l).getButtonsColumn(i).length; j++) {
						if (SimoriOn.getInstance().getLayer(l).getButtonsColumn(i)[j]) {
							System.out.println("Creating sound");
							// New test - use sound processor
							SimoriOn.getInstance().getSoundProcessor().playSound(35 + j, velocity, l);
						}
					}
				}

				if (!running.get()) {
					for (GridButton button : SimoriOn.getInstance().getGui().buttons) {
						button.setToOffState();
					}
					break;
				}

				try {
					// Sleep for an appropriate amount
					//System.out.println(Math.round(60000 / SimoriOn.getInstance().getLoopSpeed()));
					// 60000 ms (1min) / bpm (beats per minute) / 4 (4 steps per beat)
					Thread.sleep(Math.round(60000 / SimoriOn.getInstance().getLoopSpeed()/4));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}