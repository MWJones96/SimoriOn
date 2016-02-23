package JUnit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simori.SimoriOn;


public class GUITest 
{
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
	}
	
	@Test
	public void testWriteToLCD()
	{
		test.getGui().ON.doClick();
		test.getGui().writeToLCD("Test");
		assertEquals("Text in LCD does not match", test.getGui().LCD.getText(), "Test");
		test.getGui().ON.doClick();
	}
	
	@Test
	public void testTurnOffFunctionButtons()
	{
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
	}
	
	@Test
	public void testHighlightClockColumn()
	{
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
