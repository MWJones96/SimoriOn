package simori;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * Created by Giedrius on 23/02/2016.
 */
public class SoundProcessor {

    private Synthesizer synth;
    private MidiChannel[] midiChannels;

    private final static int PERCUSSION_CHANNEL = 9;
    private final static int OTHER_CHNANEL = 5;

    public SoundProcessor(){
        // Create the synthesizer
        try {
            this.synth = MidiSystem.getSynthesizer();
            this.synth.open();
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(1);
        }

        midiChannels = synth.getChannels();
    }

    public void playSound(int note, int velocity){
        int instrument = SimoriOn.getInstance().getCurrentInstrument();
        Sound sound = new Sound(this.synth, this.midiChannels[ OTHER_CHNANEL ], instrument, note, velocity);

        // Create new thread to play that sound
        (new Thread(sound)).start();
    }

    /**
     * Runnable class for creating individual sounds
     */
    public class Sound implements Runnable {

        private int note = 50;
        private int velocity = 64;
        private Synthesizer synth;
        private MidiChannel midiChannel;
        private int instrument;

        public Sound(Synthesizer synth, MidiChannel midiChannel, int instrument, int note, int velocity){
            this.note = note;
            this.velocity = velocity;
            this.synth = synth;
            this.midiChannel = midiChannel;
            this.instrument = instrument;
        }

        public void run(){
            Instrument[] instruments = synth.getDefaultSoundbank().getInstruments();

            synth.loadInstrument(instruments[instrument]);
            midiChannel.programChange(instrument);

            midiChannel.noteOn(note, velocity);
            delay(10 * velocity);
            midiChannel.noteOff(note, velocity);
        }

        public void delay(int ms) {
            try {
                Thread.sleep(ms);
            } catch (Exception ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
