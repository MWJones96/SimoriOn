package simori;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * 
 * this is a class that implements the sounds that will be played when a button
 * is clicked and the clock hand is on the button for that time period. if the
 * button is pressed and the clock hand isnt on the button, no sound will play
 * 
 * @author Team G
 * @date 10/02/2016.
 * @Verson 1.0;
 * 
 */
public class Sounds implements Runnable {

	private final static int FUDGE_FACTOR = 10; /* multiplier */
	private final static int OTHER_CHANNEL = 12; /* 0..8, 10..15 */
	private int note = 50;
	private int velocity = 64;

	Synthesizer synthesizer;

	/**
	 * constructor that takes into account 2 arguments that take into account
	 * the note being played and the velocity.
	 * 
	 * @param note
	 * @param velocity
	 */
	public Sounds(int note, int velocity) {
		this.note = note;
		this.velocity = velocity;
	}

	/**
	 * run method will play initiliase the synthesizer and then play the
	 * appropriate instrument at that specific button.
	 */
	public void run() {
		synthesizer = this.getSynthesizer();
		this.playInstrument();

	}

	/**
	 * a method that reutns the intance of the synthesizer for that specific
	 * object.
	 * 
	 * @return
	 */
	public Synthesizer getSynthesizer() {
		Synthesizer synthesizer = null;
		try {
			synthesizer = MidiSystem.getSynthesizer();
			synthesizer.open();
		} catch (Exception ex) {
			System.out.println(ex);
			System.exit(1);
		}
		return synthesizer;
	}

	/**
	 * method that has been created in order to delay the current thread that is
	 * being used for ms amount of milliseconds.
	 * 
	 * @param ms
	 */
	public void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * method that will allow the instrument to be played using the standard
	 * java libraries to play the sounds at the given button
	 */
	public void playInstrument() {
		// get the channel for the synthesizer.
		MidiChannel[] midiChannels = synthesizer.getChannels();
		MidiChannel midiChannel = midiChannels[OTHER_CHANNEL];
		Instrument[] instruments = synthesizer.getDefaultSoundbank()
				.getInstruments();

		synthesizer.loadInstrument(instruments[20]);
		midiChannel.programChange(20);
		midiChannel.noteOn(note, velocity);
		delay(FUDGE_FACTOR * velocity);
		midiChannel.noteOff(note, velocity);

	}

}
