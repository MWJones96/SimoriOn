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

    public SoundProcessor(){
        // Create the synthesizer
        try {
            this.synth = MidiSystem.getSynthesizer();
            this.synth.open();
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }

    public void playSound(int note, int velocity){
        Sound sound = new Sound(this.synth, note, velocity);

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

        public Sound(Synthesizer synth, int note, int velocity){
            this.note = note;
            this.velocity = velocity;
            this.synth = synth;
        }

        public void run(){
            MidiChannel[] midiChannels = synth.getChannels();
            MidiChannel midiChannel = midiChannels[9];
            Instrument[] instruments = synth.getDefaultSoundbank()
                    .getInstruments();

            synth.loadInstrument(instruments[20]);
            midiChannel.programChange(20);
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
