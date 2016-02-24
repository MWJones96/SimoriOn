package JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.BeforeClass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simori.SimoriOn;


public class GUITest 
{
	public SimoriOn test;
	
	/**Sets up the Simori instance before the tests
	 */
	@BeforeClass
	public void setUp() throws Exception 
	{
		test = SimoriOn.getInstance();
	}

	
	@Test
	public void testWriteToLCD()
	{
		test.getGui().writeToLCD("Test");
		assertEquals(test.getGui().LCD.getText(), "Test");
	}
	
	@Test
	public void testTurnOffFunctionButtons()
	{
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
	
	public void testHighlightRowAndColumn()
	{
		test.getGui().highlightColumnAndRow(0, 0);
		
		for(int x = 0; x < 16; x++)
			assertTrue(test.getGui().getButton(x, 0).getState());
		for(int y = 0; y < 16; y++)
			assertTrue(test.getGui().getButton(0, y).getState());
		
		test.getGui().turnOffAllButtons();
	}
	
	public void testHighlightAndColumn()
	{
		test.getGui().highlightOneColumn(0);

		for(int y = 0; y < 16; y++)
			assertTrue(test.getGui().getButton(0, y).getState());
		
		test.getGui().turnOffAllButtons();
	}

	
	@Test
	public void testHighlightClockColumn()
	{
		test.getGui().highlightClockColumn(0);
		
		for(int y = 0; y < 16; y += 5)
			assertTrue(test.getGui().getButton(0, y).getState());
				
		test.getGui().turnOffAllButtons();
	}
	

}
