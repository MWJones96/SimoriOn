package JUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simori.button.GridButton;
import simori.core.SimoriOn;
import simori.mode.VoiceChangeMode;

import static org.junit.Assert.assertEquals;

public class VoiceChangeModeTest {

	public SimoriOn test;
	VoiceChangeMode vcm;
	GridButton gb1, gb2, gb3, gb4, gb5;

	@Before
	public void setUp() throws Exception {

		// Create SimoriOn and voice change mode instances
		test = SimoriOn.getInstance();
		test.makeDisplay();
		vcm = new VoiceChangeMode();
		test.setMode(vcm);

		// Create grid button mock objects
		gb1 = new GridButton(6, 4);
		gb2 = new GridButton(14, 3);
		gb3 = new GridButton(15, 15);
		gb4 = new GridButton(12, 15);
		gb5 = new GridButton(0,0);
	}

	@Test
	public void testVoiceChangeMode(){

		// Test for correctness of the instrument
		// variable after a button is pressed

		vcm.processMatrixButton(gb1);
		assertEquals(vcm.getInstrument(), 70);

		vcm.processMatrixButton(gb2);
		assertEquals(vcm.getInstrument(), 62);

		// Defaults to last element of allInstruments array
		vcm.processMatrixButton(gb3);
		assertEquals(vcm.getInstrument(), 234);

		// Defaults to last element of allInstruments array
		vcm.processMatrixButton(gb4);
		assertEquals(vcm.getInstrument(), 234);

		vcm.processMatrixButton(gb5);
		assertEquals(vcm.getInstrument(), 0);

	}

	@After
	public void tearDown() throws Exception {
	}

}
