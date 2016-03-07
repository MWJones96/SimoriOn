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

public class ClockHand implements Runnable 
{
	//The final point at which the clock hand will go before looping back
	private int loopPoint;
	
	//The speed at which the clock will loop (in beats per minute)
	private int loopSpeed;
	
	//The velocity of the note
	private byte velocity;

	public AtomicBoolean running = new AtomicBoolean();

	/**
	 * Constructor that is used to set the default constructor for which the
	 * clock hand will run on.
	 *
	 */

	public ClockHand() 
	{
		this.loopPoint = 16;
		this.loopSpeed = 60;
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
	@Override
	public void run() 
	{
		System.out.println("Clock hand thread started");
		running.set(true);

		while (running.get())
		{
			//Cycle through all of the columns, then cycle through all the layers, then all the notes in the column
			
			for (int i = 0; i < loopPoint; i++) 
			{
				SimoriOn.getInstance().getGui().highlightClockColumn(i);

				for(int j = 0; j < SimoriOn.getInstance().getLayers().length; j++) 
				{
					for (int k = 0; k < SimoriOn.getInstance().getLayer(j).getButtonsColumn(i).length; k++) 
					{
						if (SimoriOn.getInstance().getLayer(j).getButtonsColumn(i)[k]) 
						{
							System.out.println("Creating sound");
							// New test - use sound processor
							SimoriOn.getInstance().getSoundProcessor().playSound(35 + k, velocity, j);
						}
					}
				}
				
				if(!running.get())
					break;
				
				try 
				{
					// Sleep for an appropriate amount
					//System.out.println(Math.round(60000 / SimoriOn.getInstance().getLoopSpeed()));
					// 60000 ms (1min) / bpm (beats per minute) / 4 (4 steps per beat)
					Thread.sleep(Math.round(60000 / SimoriOn.getInstance().getLoopSpeed()/4));
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			
		}
		
		//After the clock hand has been turned off, clear the matrix buttons
		for (GridButton button : SimoriOn.getInstance().getGui().buttons) 
		{
			button.setToOffState();
		}
		
	}
	
	/**
	 * method that sets the velocity for the main clock hand
	 * @param velocity
	 */
	public void setVelocity(int velocity)
	{
		this.loopSpeed = velocity;
	}

	/**
	 * method that will get the velocity back after it has been set 
	 * @return
	 */
	public int getVelocity()
	{
		return velocity;
	}

	/**
	 * method that will reset the clockhand after a specific column
	 * is selected wihtin the GUI. 
	 * @param loopPoint
	 */
	public void setLoopPoint(int loopPoint)
	{
		this.loopPoint = loopPoint;
	}

	/**
	 * method that will get the loop point value back 
	 * @return
	 */
	public int getLoopPoint()
	{
		return loopPoint;
	}
}