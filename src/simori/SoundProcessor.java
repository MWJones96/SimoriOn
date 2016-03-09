package simori;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * this is the class that deals with the changing voice mode as part of
 * Performance mode. this mode can be activated using the L1 button being
 * pressed. based on the specification, 128 and 47 instruments are being
 * implemented in the MIDI spec. once the voice is chosen, it appears on the GUI
 * LCD bar.
 * 
 * @author team G
 * @date 23/02/2016.
 */
public class SoundProcessor {

	private Synthesizer synth;
	private MidiChannel[] midiChannels;

	private final static int PERCUSSION_CHANNEL = 9;
	private final static int OTHER_CHANNEL = 5;

	// constructor that is used in order to create a synth.
	public SoundProcessor() {
		// Create the synth
		try {
			this.synth = MidiSystem.getSynthesizer();
			this.synth.open();
		} catch (Exception ex) {
			System.out.println(ex);
			System.exit(1);
		}

		midiChannels = synth.getChannels();
	}

	/**
	 * method that will play the sound based upon the note given and the
	 * velocity inputed, based on what buttons are presseed.
	 * 
	 * @param note
	 * @param velocity
	 */
	public void playSound(int note, int velocity, int layerIndex) {
		int instrument = SimoriOn.getInstance().getLayer(layerIndex)
				.getCurrentInstrument();

		Sound sound;
		// Check if instrument is percussion
		if (instrument > 127) {
			// If instrument number is higher than 127, that means it's a
			// percussion instrument
			// Change instrument into a note and change which channel to use
			note = instrument - 127;
			sound = new Sound(this.synth,
					this.midiChannels[PERCUSSION_CHANNEL], instrument, note,
					velocity);
		} else {
			sound = new Sound(this.synth, this.midiChannels[OTHER_CHANNEL],
					instrument, note, velocity);
		}

		// Create new thread to play that sound
		(new Thread(sound)).start();
	}

	/**
	 * method that returns the current Synthesizer being used.
	 * 
	 * @return
	 */
	public Synthesizer getSynth() {
		return this.synth;
	}

	/**
	 * inner class- Runnable class for creating individual sounds
	 */
	public class Sound implements Runnable {

		private int note = 50;
		private int velocity = 64;
		private Synthesizer synth;
		private MidiChannel midiChannel;
		private int instrument;

		/**
		 * constructor that is used to play unique sounds.
		 * 
		 * @param synth
		 * @param midiChannel
		 * @param instrument
		 * @param note
		 * @param velocity
		 */
		public Sound(Synthesizer synth, MidiChannel midiChannel,
				int instrument, int note, int velocity) {
			this.note = note;
			this.velocity = velocity;
			this.synth = synth;
			this.midiChannel = midiChannel;
			this.instrument = instrument;
		}

		/**
		 * method to run in its own thread
		 */
		public void run() {
			Instrument[] instruments = synth.getDefaultSoundbank()
					.getInstruments();

			synth.loadInstrument(instruments[instrument]);
			midiChannel.programChange(instrument);

			midiChannel.noteOn(note, velocity);
			delay(10 * velocity);
			midiChannel.noteOff(note, velocity);
		}

		/**
		 * create a delay to prevent threads inter-locking and causing issues
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
	}

}
