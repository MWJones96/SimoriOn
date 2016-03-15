package JUnit;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simori.core.SimoriOn;

public class ClockHandTest {

	public SimoriOn test;
	
	@Before
	public void setUp() throws Exception
	{
		test = SimoriOn.getInstance();
	}
	
	@Test
	public void testRunning()
	{
		SimoriOn.getClockHand().run();
		
		//Tests relevant buttons for state on
		for(int y = 0; y < 16; y += 5)
			assertTrue(test.getGui().getButton(0, y).getState());
		
		//Tests the rest for state off
		assertFalse(test.getGui().getButton(0, 1).getState());
		assertFalse(test.getGui().getButton(0, 2).getState());
		assertFalse(test.getGui().getButton(0, 3).getState());
		assertFalse(test.getGui().getButton(0, 4).getState());
		assertFalse(test.getGui().getButton(0, 6).getState());
		assertFalse(test.getGui().getButton(0, 7).getState());
		assertFalse(test.getGui().getButton(0, 8).getState());
		assertFalse(test.getGui().getButton(0, 9).getState());
		assertFalse(test.getGui().getButton(0, 11).getState());
		assertFalse(test.getGui().getButton(0, 12).getState());
		assertFalse(test.getGui().getButton(0, 13).getState());
		assertFalse(test.getGui().getButton(0, 14).getState());
		
		try {
			Thread.sleep(Math.round(60000 / SimoriOn.getClockHand().getLoopSpeed()/4));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Tests if the correct buttons are on or off in the next row
		for(int y = 0; y < 16; y += 5)
			assertTrue(test.getGui().getButton(1, y).getState());
		
		//Tests the rest for state off
		assertFalse(test.getGui().getButton(1, 1).getState());
		assertFalse(test.getGui().getButton(1, 2).getState());
		assertFalse(test.getGui().getButton(1, 3).getState());
		assertFalse(test.getGui().getButton(1, 4).getState());
		assertFalse(test.getGui().getButton(1, 6).getState());
		assertFalse(test.getGui().getButton(1, 7).getState());
		assertFalse(test.getGui().getButton(1, 8).getState());
		assertFalse(test.getGui().getButton(1, 9).getState());
		assertFalse(test.getGui().getButton(1, 11).getState());
		assertFalse(test.getGui().getButton(1, 12).getState());
		assertFalse(test.getGui().getButton(1, 13).getState());
		assertFalse(test.getGui().getButton(1, 14).getState());
	}

	@After
	public void tearDown() throws Exception {
	}

}
