package JUnit;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simori.GUI;
import simori.OnOffMode;
import simori.GridButton;

public class OnOffModeTest {
	public OnOffMode test;
	public GridButton button;

	@Before
	public void setUp() throws Exception {
		this.test = new OnOffMode();
		this.button = new GridButton(2,3);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testOnOffMode(){
		test.processMatrixButton(button);
		
	}

}