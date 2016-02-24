package simori;

/**
 * this class uses the singleton design pattern to enable methods to be
 * referenced from anywhere within the package using a single instance. the
 * simori-on class is the main class.
 * 
 * @author Team G
 * @Verson 1.1;
 * @date 9/02/16
 * 
 */
public class SimoriOn {
	// Self instance (Singleton pattern)
	private static SimoriOn instance = null;

	// The current mode of operation
	private Mode mode;
	// The speed of the metronome (in beats per minute)
	private int loopSpeed;
	//The point where the clock hand will loop
	private int loopPoint;
	//The length of the note
	private int velocity;
	private Layer[] layers;
	//Which layer is currently being selected
	private Layer currentLayer;
	//The GUI associated with the device
	private GUI gui;
	//The clockhand for the device
	private static ClockHand clockhand;

	// The sounds processor instance
	private SoundProcessor soundProcessor;

	// Holds instrument selected
	private int currentInstrument = 1;

	/**
	 * constructor that sets the default values for the main grid. protected in
	 * order to be accessed by other classes within the package via the
	 * singleton pattern
	 */
	protected SimoriOn() {
		mode = new OnOffMode();
		loopSpeed = 60; // TEMP
		layers = new Layer[16];
		gui = new GUI();
		soundProcessor = new SoundProcessor();
	}

	/**
	 * static method that checks to see if and instance of the SimoriOn has been
	 * created. if not, then create one
	 * 
	 * @return
	 */
	public static SimoriOn getInstance() {
		// Singleton pattern..
		if (instance == null) {
			instance = new SimoriOn();
		}
		return instance;
	}

	/**
	 * method that will be used in order to set the mode
	 * 
	 * @param mode
	 *            is an instance of the Mode class.
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	/**
	 * method that sets the BPM of the clockhand.
	 * 
	 * @param loopSpeed
	 *            is the bpm, of type int.
	 */
	public void setLoopSpeed(int bpm) {
		this.loopSpeed = bpm;
	}

	/**
	 * Retrieve the sound processor
	 * @return SoundProcessor
     */
	public SoundProcessor getSoundProcessor(){
		return this.soundProcessor;
	}

	/**
	 * Retrieve currently selected instrument number
	 * @return int
     */
	public int getCurrentInstrument(){
		return this.currentInstrument;
	}

	public void setInstrument(int instrument){
		this.currentInstrument = instrument;
	}

	/**
	 * Return the current bpm
	 * @return loopSpeed
     */
	public int getLoopSpeed(){
		return this.loopSpeed;
	}

	/**
	 * method that returns the mode that the simoriOn is currently in
	 * 
	 * @return mode
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * method that will return the GUI that is currently being used.
	 * 
	 * @return
	 */
	public GUI getGui() {
		return gui;
	}

	/**
	 * method that sets the objet to the clockhand variable being used.
	 * 
	 * @param c
	 */
	public static void setClockHand(ClockHand c) {
		clockhand = c;
	}

	/**
	 * method that returns the clockhand that is being used.
	 * 
	 * @return clockhand that is bieng used
	 */
	public static ClockHand getClockHand() {
		return clockhand;
	}

}