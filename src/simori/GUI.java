package simori;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
	//Panel containing all data to be sent to the frame; grid containing all matrix buttons
	public JPanel panel = new JPanel(); 
	public JPanel grid = new JPanel();
	
	//A simple label
	public JLabel display = new JLabel("Action:"); 
	
	//Displays information relevant to current function
	public JTextField LCD = new JTextField(15);

	// Left buttons
	public FunctionButton L1 = new FunctionButton("L1"), L2 = new FunctionButton("L2"),
				   L3 = new FunctionButton("L3"), L4 = new FunctionButton("L4");

	// Right buttons
	public FunctionButton R1 = new FunctionButton("R1"), R2 = new FunctionButton("R2"),
				   R3 = new FunctionButton("R3"), R4 = new FunctionButton("R4");

	// ON/OFF button; OK button
	public JButton ON = new JButton(), OK = new JButton();

	// Array for 16x16 grid buttons
	public GridButton buttons[] = new GridButton[16 * 16];

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
		L1.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffL1.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		L2.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffL2.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		L3.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffL3.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		L4.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffL4.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		
		R1.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffR1.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		R2.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffR2.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		R3.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffR3.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		R4.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffR4.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		
		ON.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffON.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		OK.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffOK.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		
		//set default values for the grid and grid dimensions
		panel.setLayout(null);
		grid.setLayout(new GridLayout(16, 16)); grid.setBounds(100, 100, 500, 500);

		// Set position/size of left buttons
		L1.setBounds(20, 100, 50, 50); L2.setBounds(20, 200, 50, 50);
		L3.setBounds(20, 300, 50, 50); L4.setBounds(20, 400, 50, 50);

		// Set position/size of right buttons
		R1.setBounds(625, 100, 50, 50); R2.setBounds(625, 200, 50, 50);
		R3.setBounds(625, 300, 50, 50); R4.setBounds(625, 400, 50, 50);

		// Set position/size of top/bottom buttons
		ON.setBounds(325, 40, 50, 50); OK.setBounds(550, 605, 50, 50); LCD.setBounds(200, 605, 300, 50);
		display.setBounds(150, 605, 300, 50);
		
		//Changes TextBox to look more like an LCD
		LCD.setEditable(false); LCD.setBackground(Color.LIGHT_GRAY); 
		LCD.setFont(new Font("Arial", Font.BOLD, 12));
		LCD.setForeground(Color.BLACK); LCD.setHorizontalAlignment(SwingConstants.CENTER);

		// Create and add grid buttons
		for (int i = 0; i < 16 * 16; i++) 
		{
			buttons[i] = new GridButton(i % 16, (int) 15 - (i / 16));
			buttons[i].setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffGRID.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			grid.add(buttons[i]);
		}

		// Add everything to the overall panel
		panel.add(L1); panel.add(L2); panel.add(L3); panel.add(L4);
		panel.add(R1); panel.add(R2); panel.add(R3); panel.add(R4);
		panel.add(ON); panel.add(OK); panel.add(LCD); panel.add(display);
		panel.add(grid);
		
		ON.setOpaque(false);
		ON.setContentAreaFilled(false);
		ON.setBorderPainted(false);
		
		OK.setOpaque(false);
		OK.setContentAreaFilled(false);
		OK.setBorderPainted(false);

		ON.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(SimoriOn.getInstance().getMode() instanceof OnOffMode)
				{
					ON.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOnON.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					SimoriOn.getInstance().setMode(new PerformanceMode());
				}
				else
				{
					ON.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffON.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					SimoriOn.getInstance().setMode(new OnOffMode());
					turnOffFunctionButtons();
					turnOffGridButtons();
				}
			}

		});

		
		OK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SimoriOn.getInstance().getMode().processOKButton();
				
				//Implements button flash as a thread, so that other processes won't be stopped for 250ms
				new Thread(){
					
					public void run()
					{
						OK.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOnOK.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
						try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						OK.setIcon(new ImageIcon(new ImageIcon("./res/ButtonOffOK.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					}
					
				}.start();
			}

		});

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
		
		//Sets favicon of the frame
		frame.setIconImage(new ImageIcon("./res/ButtonOnGRID.png").getImage());
		
		frame.setLocation(screenWidth / 4, screenHeight / 8);
		// Add gui panel to JFrame
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * get method to return gui instance from JPanel.
	 * 
	 * @return gui that will be used for the entire UI.
	 */
	public JPanel getGui() {
		return this.panel;
	}
	
	public void writeToLCD(String text)
	{
		LCD.setText(text);
	}
	
	public GridButton[] getButtons()
	{
		return buttons;
	}

	public GridButton getButton(int x, int y) {
		return buttons[240 - (y * 16) + x];
	}
	
	/**Turns off all function buttons L1-4,
	 * and R1-4
	 */
	public void turnOffFunctionButtons()
	{
		L1.turnOff();
		L2.turnOff();
		L3.turnOff();
		L4.turnOff();
		R1.turnOff();
		R2.turnOff();
		R3.turnOff();
		R4.turnOff();
	}
	
	/**Turns of all of the grid buttons
	 * 
	 */
	public void turnOffGridButtons()
	{
		for(GridButton b : buttons)
		{
			b.setToOffState();
		}
	}

	public void highlightColumnAndRow(int x, int y) {
		// Turn off all buttons
		turnOffGridButtons();
		// Highlight buttons in the same row and column
		for (int i = 0; i < 16; i++) {
			getButton(x, i).setToOnState();
			getButton(i, y).setToOnState();
		}
	}

	/**
	 * 
	 * @param x
	 */
	public void highlightClockColumn(int x) 
	{
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

	/**
	 * 
	 * @param x
	 */
	public void highlightOneColumn(int x) 
	{
		// Turn off all buttons
		for (GridButton button : buttons) 
		{
			if (!(GridButton.getButtonsSelected().contains(button))) {
				button.setToOffState();
			}
		}
		
		// Highlight every button in the same column
		for (int i = 0; i < 16; i += 1) {
			getButton(x, i).setToOnState();
		}
		
	}
}