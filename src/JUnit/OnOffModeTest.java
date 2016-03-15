package JUnit;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simori.button.GridButton;
import simori.core.ClockHand;
import simori.core.GUI;
import simori.mode.OnOffMode;

public class OnOffModeTest {
	public OnOffMode test;
	public GridButton button;
	public ClockHand clockhand;

	@Before
	public void setUp() throws Exception {
		this.test = new OnOffMode();
		this.button = new GridButton(2,3);
		this.clockhand = new ClockHand();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testOnOffMode(){
		test.processMatrixButton(button);
		
	}

}
