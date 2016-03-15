package JUnit;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simori.SimoriOn;
import simori.button.GridButton;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class GridButtonTest {

	public SimoriOn test;
	@Before
	public void setUp() throws Exception
	{
		test = SimoriOn.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testGdridButtonConstructor(){
		GridButton gb = new GridButton(0, 0);
		GridButton egb = new GridButton(0, 0);
		
		assertEquals(gb,egb);
	}
}
