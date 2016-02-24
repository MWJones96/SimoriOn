package JUnit;

import static org.junit.Assert.assertEquals;
<<<<<<< HEAD
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.BeforeClass;
=======

import org.junit.After;
import org.junit.Before;
>>>>>>> b05c3348db6fa43f353c1d976e16cabe1d530a73
import org.junit.Test;

import simori.SimoriOn;


public class GUITest 
{
<<<<<<< HEAD
	public SimoriOn test;
	
	/**Sets up the Simori instance before the tests
	 */
	@BeforeClass
	public void setUp() throws Exception 
	{
		test = SimoriOn.getInstance();
=======
	public SimoriOn test = SimoriOn.getInstance();
	
	/**Re-initialises the Simori-ON before every test
	 * in order to isolate tests (Isolation - key property
	 * of TRIP)
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
>>>>>>> b05c3348db6fa43f353c1d976e16cabe1d530a73
	}
	
	@Test
	public void testWriteToLCD()
	{
<<<<<<< HEAD
		test.getGui().writeToLCD("Test");
		assertEquals(test.getGui().LCD.getText(), "Test");
=======
		test.getGui().ON.doClick();
		test.getGui().writeToLCD("Test");
		assertEquals("Text in LCD does not match", test.getGui().LCD.getText(), "Test");
		test.getGui().ON.doClick();
>>>>>>> b05c3348db6fa43f353c1d976e16cabe1d530a73
	}
	
	@Test
	public void testTurnOffFunctionButtons()
	{
<<<<<<< HEAD
		test.getGui().L1.turnOn();
		test.getGui().R3.turnOn();
		test.getGui().turnOffFunctionButtons();
		assertTrue(test.getGui().L1.getState());
		assertTrue(test.getGui().L2.getState());
		assertTrue(test.getGui().L3.getState());
		assertTrue(test.getGui().L4.getState());
		assertTrue(test.getGui().R1.getState());
		assertTrue(test.getGui().R2.getState());
		assertTrue(test.getGui().R3.getState());
		assertTrue(test.getGui().R4.getState());
	}
	

	@Test
	public void testHighlightRowAndColumn()
	{
		test.getGui().highlightColumnAndRow(0, 0);
		
		for(int x = 0; x < 16; x++)
			assertTrue(test.getGui().getButton(x, 0).getState());
		for(int y = 0; y < 16; y++)
			assertTrue(test.getGui().getButton(0, y).getState());
		
		test.getGui().turnOffAllButtons();
	}
	
	@Test
	public void testHighlightColumn()
	{
		test.getGui().highlightOneColumn(0);
		
		for(int x = 0; x < 16; x++)
			assertTrue(test.getGui().getButton(x, 0).getState());
		
		test.getGui().turnOffAllButtons();
=======
		test.getGui().ON.doClick();
		test.getGui().L1.doClick();
		test.getGui().turnOffFunctionButtons();
		assertEquals("One or more button not turned off", test.getGui().L1.getState(), false);
		assertEquals("One or more button not turned off", test.getGui().L2.getState(), false);
		assertEquals("One or more button not turned off", test.getGui().L3.getState(), false);
		assertEquals("One or more button not turned off", test.getGui().L4.getState(), false);
		assertEquals("One or more button not turned off", test.getGui().R1.getState(), false);
		assertEquals("One or more button not turned off", test.getGui().R2.getState(), false);
		assertEquals("One or more button not turned off", test.getGui().R3.getState(), false);
		assertEquals("One or more button not turned off", test.getGui().R4.getState(), false);
		test.getGui().ON.doClick();
	}
	
	@Test
	public void testTurnOffAllButtons()
	{
		test.getGui().ON.doClick();
		test.getGui().buttons[67].doClick();
		test.getGui().buttons[176].doClick();
		test.getGui().turnOffAllButtons();
		for(int i = 0; i < 16 * 16; i++)
			assertEquals("Button not in correct state", test.getGui().buttons[i].getState(), false);
		test.getGui().ON.doClick();
	}

	@Test
	public void testHighlightRowAndColumn()
	{
		test.getGui().ON.doClick();
		test.getGui().L1.doClick();
		test.getGui().getButton(0, 0).doClick();
		//for(int x = 0; x < 16; x++)
			//assertEquals("X co-ords not correct", test.getGui().getButton(x, 0).getState(), true);
		for(int y = 0; y < 16; y++)
			assertEquals("Y co-ords not correct", test.getGui().getButton(0, y).getState(), true);
		test.getGui().ON.doClick();
>>>>>>> b05c3348db6fa43f353c1d976e16cabe1d530a73
	}
	
	@Test
	public void testHighlightClockColumn()
	{
<<<<<<< HEAD
		test.getGui().highlightClockColumn(0);
		
		for(int y = 0; y < 16; y += 5)
			assertTrue(test.getGui().getButton(0, y).getState());
				
		test.getGui().turnOffAllButtons();
	}
	
	@After
	public void tearDown() throws Exception 
	{
		
	}

}
s
=======
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
>>>>>>> b05c3348db6fa43f353c1d976e16cabe1d530a73
