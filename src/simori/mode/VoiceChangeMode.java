package simori.mode;

import javax.sound.midi.Instrument;

import simori.button.GridButton;
import simori.core.SimoriOn;

/**
 * press the “L1” button. Pressing a matrix button causes it and all those in the same
 * vertical/horizontal line (only) to be set. The coordinates of
 * the pressed matrix button are used to choose an instrument from the 128
 * conventional and 47 percussion instruments given in the General MIDI
 * specification. The name of the chosen instrument is displayed on the LCD
 * display. Pressing the “OK” button causes the Simori-ON to set the voice for
 * the current layer to be the chosen instrument and then to continue to
 * Performance Mode (see Section 2.2.2).
 * 
 * @author Team G
 * @Verson 1.2;
 * @date 10/02/16
 */
public class VoiceChangeMode implements Mode {

	private Instrument[] allInstruments;
	private int instrument;

	/**
	 * constructor that checks to see if the clockhand is in existance yet. if
	 * it is not, then running will be set to false, and will not move.
	 */
	public VoiceChangeMode() {
		if (SimoriOn.getClockHand() != null) {
			SimoriOn.getClockHand().running.set(false);
		}
		SimoriOn.getInstance().getGui().turnOffGridButtons();

		this.allInstruments = SimoriOn.getInstance().getSoundProcessor().getSynth().getDefaultSoundbank()
				.getInstruments();
	}

	/**
	 * overriding method from the Mode class to enable functionality of the
	 * buttons.
	 */
	@Override
	public void processMatrixButton(GridButton button) {

		System.out.println("Matrix button processed in Voice Change Mode");

		// Highlight row and column of the button
		button.getGUI().highlightColumnAndRow(button.getCoordsX(), button.getCoordsY());

		//Normal instrument
		if(button.getCoordsY() * 16 + button.getCoordsX() < 128)
			this.instrument = button.getCoordsY() * 16 + button.getCoordsX();
		else
			//Percussion

		// Write out instrument name onto LCD
		SimoriOn.getInstance().getGui().writeToLCD("Instrument: " + allInstruments[instrument].getName());

	}

	/**
	 * method that is used when the OK button is pressed. this sets the LCD back
	 * to NULL and get the current instrument that is being used. now it will
	 * also back to the performance mode.
	 */
	public void processOKButton() {
		SimoriOn.getInstance().getGui().LCD.setText(null);
		SimoriOn.getInstance().getCurrentLayer().setInstrument(this.instrument);
		SimoriOn.getInstance().setMode(new PerformanceMode());
		SimoriOn.getInstance().getGui().turnOffFunctionButtons();

	}

	public int getInstrument() {
		return instrument;
	}

}