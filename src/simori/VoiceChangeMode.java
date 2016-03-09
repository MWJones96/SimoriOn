package simori;

import javax.sound.midi.Instrument;

/**
 * class that has been created to allow the user to select the voice of the
 * performance mode.
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

		this.allInstruments = SimoriOn.getInstance().getSoundProcessor()
				.getSynth().getDefaultSoundbank().getInstruments();
	}

	/**
	 * overriding method from the Mode class to enable functionality of the
	 * buttons.
	 */
	@Override
	public void processMatrixButton(GridButton button) {

		System.out.println("Matrix button processed in Voice Change Mode");

		// Highlight row and column of the button
		button.getGUI().highlightColumnAndRow(button.getCoordsX(),
				button.getCoordsY());

		// Set instrument variable
		if (button.getCoordsX() * button.getCoordsY() <= 128) {
			this.instrument = button.getCoordsX() * button.getCoordsY();
		} else {
			this.instrument = 128;
		}

		// Write out instrument name onto LCD
		SimoriOn.getInstance()
				.getGui()
				.writeToLCD(
						"Instrument: " + allInstruments[instrument].getName());

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

}
