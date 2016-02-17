package simori;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * I class that creates basic Layout for Sprint 1. Class creates main board for
 * Simori-ON and simulates all buttons, but does not include full functionality
 * for Sprint 1. Class uses the Runnable interface to allow GUI to run within
 * its own thread. Buttons include: 4 buttons for the left, 4 buttons for the
 * right ON/OFF button and 16x16 grid buttons.
 * 
 * @author Team G
 * @version 1.0
 * @date 05-02-2016
 */
public class GUI 
{
	//Panel containing all data to be sent to the frame; grid containing all main buttons
	private JPanel panel, grid = new JPanel();
	
	//A simple label
	JLabel display = new JLabel("Action:"); 
	
	//Displays information relevant to current function
	JTextField LCD = new JTextField(15);

	// Left buttons
	JButton L1, L2, L3, L4 = new JButton();

	// Right buttons
	JButton R1, R2, R3, R4 = new JButton();

	// ON/OFF button; OK button
	JButton ON, OK = new JButton();

	// Array for 16x16 grid buttons
	GridButton buttons[] = new GridButton[16 * 16];

	/**
	 * Constructor for GUI without any parameters. It is used in order to create
	 * the default style of how the Simori-ON board will look. sets height and
	 * width and location of buttons and creates default grid to be used for the
	 * Simori-ON.
	 * 
	 * @return void - contructor sets default values so nothing is returned.
	 */
	public GUI() 
	{
		// set default values for the grid and grid dimensions
		panel.setLayout(null);
		grid.setLayout(new GridLayout(16, 16)); grid.setBounds(100, 100, 500, 500);

		// Set position/size of left buttons
		L1.setBounds(20, 100, 55, 55); L2.setBounds(20, 200, 55, 55);
		L3.setBounds(20, 300, 55, 55); L4.setBounds(20, 400, 55, 55);

		// Set position/size of right buttons
		R1.setBounds(625, 100, 55, 55); R2.setBounds(625, 200, 55, 55);
		R3.setBounds(625, 300, 55, 55); R4.setBounds(625, 400, 55, 55);

		// Set position/size of top/bottom buttons
		ON.setBounds(320, 50, 60, 50); OK.setBounds(482, 600, 60, 50); LCD.setBounds(150, 600, 300, 50);
		LCD.setEditable(false); display.setBounds(100, 600, 300, 50);

		// Create and add grid buttons
		for (int i = 0; i < 16 * 16; i++) 
		{
			buttons[i] = new GridButton(i % 16, (int) i / 16);
			grid.add(buttons[i]);
		}

		// Add everything to the overall panel
		panel.add(L1); panel.add(L2); panel.add(L3); panel.add(L4);
		panel.add(R1); panel.add(R2); panel.add(R3); panel.add(R4);
		panel.add(ON); panel.add(OK); panel.add(LCD); panel.add(display);
		panel.add(grid);

		// Event handlers for ON and OK
		ON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If clicked in OnOfMode then switch to performance mode
				// Else set all buttons to null colour
				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {
					SimoriOn.getInstance().setMode(new PerformanceMode());
					ON.setBackground(Color.GREEN);
					for(GridButton b : buttons)
						b.setToOffState();
				} else {
					for(GridButton b : buttons)
						b.setToDisabledState();
					L1.setBackground(null);
					L2.setBackground(null);
					L3.setBackground(null);
					L4.setBackground(null);
					R1.setBackground(null);
					R2.setBackground(null);
					R3.setBackground(null);
					R4.setBackground(null);
					SimoriOn.getInstance().setMode(new OnOffMode());
					ON.setBackground(null);
				}
				System.out.println("ON/OFF button clicked");
				for (GridButton button : buttons) {
					button.setToOffState();
				}
			}
		});

		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("OK button clicked");
			}
		});

		// Event handlers for L1-4
		// If Left button clicked in OnOfMode then do nothing
		// Else if clicked in select mode already then deselect Left button
		// Else set Left and Right buttons to null colour
		L1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("L1 button clicked");
				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {
					// If OnOffMode, then do nothing
					return;
				} else if (L1.getBackground() == Color.ORANGE) {
					L1.setBackground(null);
					SimoriOn.getInstance().setMode(new PerformanceMode());
				} else {
					SimoriOn.getInstance().setMode(new VoiceChangeMode());
					L1.setBackground(Color.ORANGE);
					R1.setBackground(null);
					R2.setBackground(null);
					R3.setBackground(null);
					R4.setBackground(null);
					L2.setBackground(null);
					L3.setBackground(null);
					L4.setBackground(null);
					for (GridButton button : buttons) {
						button.setToOffState();
					}
				}
			}
		});

		L2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("L2 button clicked");

				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {
					// If OnOffMode, then do nothing
					return;
				} else if (L2.getBackground() == Color.ORANGE) {
					L2.setBackground(null);
				} else {
					L2.setBackground(Color.ORANGE);
					R1.setBackground(null);
					R2.setBackground(null);
					R3.setBackground(null);
					R4.setBackground(null);
					L1.setBackground(null);
					L3.setBackground(null);
					L4.setBackground(null);
					for (GridButton button : buttons) {
						button.setToOffState();
					}
				}
			}
		});

		L3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("L3 button clicked");

				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {
					// If OnOffMode, then do nothing
					return;
				} else if (L3.getBackground() == Color.ORANGE) {
					L3.setBackground(null);
				} else {
					L3.setBackground(Color.ORANGE);
					R1.setBackground(null);
					R2.setBackground(null);
					R3.setBackground(null);
					R4.setBackground(null);
					L1.setBackground(null);
					L2.setBackground(null);
					L4.setBackground(null);
					for (GridButton button : buttons) {
						button.setToOffState();
					}
				}
			}

		});

		L4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("L4 button clicked");

				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {
					// If OnOffMode, then do nothing
					return;
				} else if (L4.getBackground() == Color.ORANGE) {
					L4.setBackground(null);
				} else {
					L4.setBackground(Color.ORANGE);
					R1.setBackground(null);
					R2.setBackground(null);
					R3.setBackground(null);
					R4.setBackground(null);
					L1.setBackground(null);
					L2.setBackground(null);
					L3.setBackground(null);
					for (GridButton button : buttons) {
						button.setToOffState();
					}
				}
			}
		});

		// Event handlers for R1-4
		// If Right button clicked in OnOfMode then do nothing
		// Else if clicked in select mode already then deselect Right button
		// Else set Right and Left buttons to null colour
		R1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("R1 button clicked");
				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {
					// If OnOffMode, then do nothing
					return;
				} else if (R1.getBackground() == Color.ORANGE) {
					R1.setBackground(null);
				} else {
					R1.setBackground(Color.ORANGE);
					R2.setBackground(null);
					R3.setBackground(null);
					R4.setBackground(null);
					L1.setBackground(null);
					L2.setBackground(null);
					L3.setBackground(null);
					L4.setBackground(null);
					for (GridButton button : buttons) {
						button.setToOffState();
					}
				}
			}
		});

		R2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("R2 button clicked");
				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {
					// If OnOffMode, then do nothing
					return;
				} else if (R2.getBackground() == Color.ORANGE) {
					R2.setBackground(null);
				} else {
					R2.setBackground(Color.ORANGE);
					R1.setBackground(null);
					R3.setBackground(null);
					R4.setBackground(null);
					L1.setBackground(null);
					L2.setBackground(null);
					L3.setBackground(null);
					L4.setBackground(null);
					for (GridButton button : buttons) {
						button.setToOffState();
					}
				}
			}
		});

		R3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("R3 button clicked");

				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {
					// If OnOffMode, then do nothing
					return;
				} else if (R3.getBackground() == Color.ORANGE) {
					R3.setBackground(null);
				} else {
					R3.setBackground(Color.ORANGE);
					R1.setBackground(null);
					R2.setBackground(null);
					R4.setBackground(null);
					L1.setBackground(null);
					L2.setBackground(null);
					L3.setBackground(null);
					L4.setBackground(null);
					for (GridButton button : buttons) {
						button.setToOffState();
					}
				}
			}
		});

		R4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("R4 button clicked");

				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {
					// If OnOffMode, then do nothing
					return;
				} else if (R4.getBackground() == Color.ORANGE) {
					R4.setBackground(null);
				} else {
					R4.setBackground(Color.ORANGE);
					R1.setBackground(null);
					R2.setBackground(null);
					R3.setBackground(null);
					L1.setBackground(null);
					L2.setBackground(null);
					L3.setBackground(null);
					L4.setBackground(null);
					for (GridButton button : buttons) {
						button.setToOffState();
					}
				}
			}
		});
		
		Runnable runnable = new Runnable() {

			public void run() {
				JFrame frame = new JFrame("Simori-ON");
				// set location in center of screen
				frame.setLocation(400, 100);
				frame.setPreferredSize(new Dimension(700, 690));
				frame.setResizable(false);

				// set screen size to adapt to different screen dimensions
				// should stay in centre of screen when executed on all
				// screens.
				Toolkit screen = Toolkit.getDefaultToolkit();
				Dimension screenSize = screen.getScreenSize();
				int screenWidth = screenSize.width;
				int screenHeight = screenSize.height;
				frame.setLocation(screenWidth / 4, screenHeight / 8);
				// Add gui panel to JFrame
				frame.add(panel);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		};
		SwingUtilities.invokeLater(runnable);

	}

	/**
	 * get method to return gui instance from JPanel.
	 * 
	 * @return gui that will be used for the entire UI.
	 */
	public JPanel getGui() {
		return this.panel;
	}
	
	public GridButton[] getButtons()
	{
		return buttons;
	}

	public GridButton getButton(int x, int y) {
		return buttons[y * 16 + x];
	}
	
	public void turnOnAllButtons()
	{
		for(GridButton b : buttons)
		{
			b.setToOnState();
		}
	}
	
	public void turnOffAllButtons()
	{
		for(GridButton b : buttons)
		{
			b.setToOffState();
		}
	}
	
	public void disableAllButtons()
	{
		for(GridButton b : buttons)
		{
			b.setToDisabledState();
		}
	}

	public void highlightColumnAndRow(int x, int y) {
		// Turn off all buttons
		for (int i = 0; i < this.buttons.length; i++) {
			buttons[i].setToOffState();
		}
		// Highlight buttons in the same row and column
		for (int i = 0; i < 16; i++) {
			getButton(x, i).setToOnState();
			getButton(i, y).setToOnState();
		}
	}

	public void highlightColumn(int x) {

		// Turn off all buttons
		for (GridButton button : buttons) {
			if (!(GridButton.getButtonsSelected().contains(button))) {
				button.setToOffState();
			}
		}

		// Highlight every 5 buttons in the same column
		for (int i = 0; i < 16; i += 5) {
			getButton(x, i).setToOnState();
		}

	}
}
