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

	// The current mode
	private Mode mode;
	// The speed of the metronome (in beats per minute)
	private int bpm;
	private Layer[] layers;
	private GUI gui;
	private static ClockHand clockhand;

	/**
	 * constructor that sets the default values for the main grid. protected in
	 * order to be accessed by other classes within the package via the
	 * singleton pattern
	 */
	protected SimoriOn() {
		bpm = 60; // TEMP
		layers = new Layer[16];
		gui = GUI.makeGUI();
		mode = new OnOffMode();
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
	 * @param bpm
	 *            is the bpm, of type int.
	 */
	public void setBPM(int bpm) {
		this.bpm = bpm;
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