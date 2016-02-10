package simori;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/** Sound thread class which plays a sound
 *  with the given note and velocity.
 * @author Alex Sahin
 * @date 10/02/2016.
 */
public class Sounds implements Runnable {

    private final static int FIVE_SECONDS = 5000; /* milliseconds */
    private final static int FUDGE_FACTOR = 10; /* multiplier */
    private final static int OTHER_CHANNEL = 12; /* 0..8, 10..15 */
    private final static int PIANO = 1;
    private final static int PERCUSSION_CHANNEL = 9;
    private final static int CRASH_CYMBAL_1 = 49;
    private int note = 50;
    private int velocity = 64;

    Synthesizer synthesizer;

    public Sounds(int note, int velocity)
    {
        this.note = note;
        this.velocity = velocity;
    }

    public void run()
    {
        synthesizer = this.getSynthesizer();
        this.playInstrument();

    }


    public Synthesizer getSynthesizer() {
        Synthesizer synthesizer = null;
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
        } catch (Exception ex) {
            System.out.println(ex); System.exit(1);
        }
        return synthesizer;
    }


    /*
     * Delay for a number of milliseconds.
     */
    public void delay( int ms ) {
        try {
            Thread.sleep( ms );
        } catch( Exception ex ) {
            Thread.currentThread().interrupt();
        }
    }

    public void playInstrument() {
        MidiChannel[] midiChannels = synthesizer.getChannels();
        MidiChannel midiChannel = midiChannels[OTHER_CHANNEL];
        Instrument[] instruments = synthesizer.getDefaultSoundbank().getInstruments();

        synthesizer.loadInstrument(instruments[PIANO]);
        midiChannel.programChange(PIANO);
        midiChannel.noteOn(note, velocity);
        delay(FUDGE_FACTOR * velocity);
        midiChannel.noteOff(note, velocity);

    }

}
