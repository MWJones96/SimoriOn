package simori;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * this class uses the singleton design pattern to enable methods to be
 * referenced from anywhere within the package using a single instance. The
 * Simori-ON class is the main class.
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

	// The layers that are in the device
	private Layer[] layers = new Layer[16];

	// The layer which is currently being sent to the GUI
	private Layer currentLayer;

	// The GUI associated with the device
	private GUI gui;

	// The clock hand for the device
	private static ClockHand clockHand;

	// The sounds processor instance
	private SoundProcessor soundProcessor;

	/**
	 * constructor that sets the default values for the main grid. protected in
	 * order to be accessed by other classes within the package via the
	 * singleton pattern
	 */
	private SimoriOn() {
		mode = new OnOffMode();
		for (int i = 0; i < 16; i++) {
			layers[i] = new Layer();
		}
		currentLayer = layers[0];
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
	 * Makes the display which houses the GUI
	 */
	public void makeDisplay() {
		new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Simori-ON");

				// Panel containing all data to be sent to the frame; grid
				// containing all matrix buttons
				JPanel panel = new JPanel(), grid = new JPanel();

				// set default values for the grid and grid dimensions
				panel.setLayout(null);
				grid.setLayout(new GridLayout(16, 16));
				grid.setBounds(100, 100, 500, 500);

				// Create and add grid buttons
				for (int i = 0; i < 16 * 16; i++) {
					gui.buttons[i] = new GridButton(i % 16, (int) 15 - (i / 16));
					gui.buttons[i].setIcon(new ImageIcon(new ImageIcon(
							"./res/ButtonOffGRID.png").getImage()
							.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
					grid.add(gui.buttons[i]);
				}

				// Add everything to the overall panel
				panel.add(gui.L1);
				panel.add(gui.L2);
				panel.add(gui.L3);
				panel.add(gui.L4);
				panel.add(gui.R1);
				panel.add(gui.R2);
				panel.add(gui.R3);
				panel.add(gui.R4);
				panel.add(gui.ON);
				panel.add(gui.OK);
				panel.add(gui.LCD);
				panel.add(gui.display);
				panel.add(grid);

				// set location in center of screen
				frame.setLocation(400, 100);
				frame.setPreferredSize(new Dimension(700, 695));
				frame.setResizable(false);
				// set screen size to adapt to different screen dimensions
				// should stay in centre of screen when executed on all
				// screens.
				Toolkit screen = Toolkit.getDefaultToolkit();
				Dimension screenSize = screen.getScreenSize();
				int screenWidth = screenSize.width;
				int screenHeight = screenSize.height;

				// Sets fav-icon of the frame
				frame.setIconImage(new ImageIcon("./res/ButtonOnGRID.png")
						.getImage());

				frame.setLocation(screenWidth / 4, screenHeight / 8);
				// Add gui panel to JFrame
				frame.add(panel);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		}.run();
	}

	/**
	 * Resets all of the attributes of SimoriON without redrawing all of the GUI
	 * elements.
	 * 
	 * Used when the device is switched off/for testing purposes
	 * 
	 */
	public void resetDevice() {
		mode = new OnOffMode();
		for (int i = 0; i < 16; i++) {
			layers[i] = new Layer();
		}
		currentLayer = layers[0];
		soundProcessor = new SoundProcessor();
		setClockHand(new ClockHand());
		gui.resetGui();
	}

	/**
	 * method used to set the mode of the Simori On Device
	 * 
	 * @param mode
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	/**
	 * method used to return the sound that is currently being used
	 * 
	 * @return SoundProcessor instance
	 */
	public SoundProcessor getSoundProcessor() {
		return this.soundProcessor;
	}

	/**
	 * method that will give back the current layer being processed
	 * 
	 * @return Layer instance
	 */
	public Layer getCurrentLayer() {
		return this.currentLayer;
	}

	/**
	 * method that gets all the layers back as an array
	 * 
	 * @return Layer array instance.
	 */
	public Layer[] getLayers() {
		return this.layers;
	}

	/**
	 * method that will return a specific layer based on its value
	 * 
	 * @param i
	 * @return
	 */
	public Layer getLayer(int i) {
		return this.layers[i];
	}

	/**
	 * method that sets the current layer therefore needs the layer to be set to
	 * be an instance of layer
	 * 
	 * @param layer
	 */
	public void setCurrentLayer(Layer layer) {
		this.currentLayer = layer;
	}

	/**
	 * set the current layer using the index value of the layer.
	 * 
	 * @param index
	 */
	public void setCurrentLayer(int index) {
		this.currentLayer = this.layers[index];
	}

	/**
	 * method that returns the mode in use at the moment.
	 * 
	 * @return
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * method that gets the current instance of the GUI being used.
	 * 
	 * @return
	 */
	public GUI getGui() {
		return gui;
	}

	/**
	 * method that gets returns the clock hand that is currently being used.
	 * 
	 * @return
	 */
	public static ClockHand getClockHand() {
		return clockHand;
	}

	/**
	 * method that allows the clock hand to be set. the parameter has to be the
	 * current clock hand that is being used.
	 * 
	 * @param c
	 */
	public static void setClockHand(ClockHand c) {
		clockHand = c;
	}

}