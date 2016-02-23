package JUnit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simori.SimoriOn;


public class GUITest 
{
	public SimoriOn test;
	
	@Before
	public void setUp() throws Exception 
	{
		this.test = SimoriOn.getInstance();
	}
	
	@Test
	public void testWriteToLCD()
	{
		test.getGui().writeToLCD("Test");
		assertEquals("Text in LCD does not match", test.getGui().LCD.getText(), "Test");
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
	}
	
	@Test
	public void testTurnOffAllButtons()
	{
		test.getGui().ON.doClick();
		test.getGui().buttons[67].doClick();
		test.getGui().buttons[276].doClick();
		test.getGui().turnOffAllButtons();
		for(int i = 0; i < 16 * 16; i++)
			assertEquals("Button not in correct state", test.getGui().buttons[i].getState(), false);
	}

	@After
	public void tearDown() throws Exception {
	}

}
