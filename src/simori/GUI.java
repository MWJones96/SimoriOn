package simori;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 640032165, 640025919, 640041749
 * @version 1.0
 * @since 05-02-2016 GUI class that creates basic Layout for Sprint 1. Class
 * creates main board for Simori-ON and simulates all buttons, but does not
 * include full functionality for Sprint 1. Class uses the Runnable interface to
 * allow GUI to run within its own thread. Buttons include: 4 buttons for the
 * left, 4 buttons for the right ON/OFF button and 16x16 grid buttons. Class
 * outline by 640025919, main functionality by 640032165
 */
public class GUI 
{
    private JPanel gui = new JPanel();
    private JPanel grid = new JPanel();
    private ClockHand clockHand;
    //uneditable label
    JLabel display = new JLabel("Action:");
    //empty text box with font-size : 15
    JTextField LCD = new JTextField(15);

    // Left buttons
    JButton L1 = new JButton("L1");
    JButton L2 = new JButton("L2");
    JButton L3 = new JButton("L3");
    JButton L4 = new JButton("L4");

    // Right buttons
    JButton R1 = new JButton("R1");
    JButton R2 = new JButton("R2");
    JButton R3 = new JButton("R3");
    JButton R4 = new JButton("R4");

    //ON/OFF button
    JButton ON = new JButton("ON");

    //OK button
    JButton OK = new JButton("OK");

    // Array for 16x16 grid buttons
    GridButton buttons[] = new GridButton[16 * 16];

    /**
     * Constructor for GUI without any parameters. It is used in order to create
     * the default style of how the Simori-ON board will look. sets height and
     * width and location of buttons and creates default grid to be used for the
     * Simori-ON.
     * in future sprints, this layout will be changed to 
     * circular buttons in order to match the spec identically
     *
     * @return void - constructor sets default values so nothing is returned.
     */
    public GUI() 
    {
        //set default values for the grid and grid dimensions
        gui.setLayout(null);
        grid.setLayout(new GridLayout(16, 16));
        grid.setBounds(100, 100, 500, 500);

        // Set position/size of left buttons
        L1.setBounds(20, 100, 55, 55);
        L2.setBounds(20, 200, 55, 55);
        L3.setBounds(20, 300, 55, 55);
        L4.setBounds(20, 400, 55, 55);

        // Set position/size of right buttons
        R1.setBounds(625, 100, 55, 55);
        R2.setBounds(625, 200, 55, 55);
        R3.setBounds(625, 300, 55, 55);
        R4.setBounds(625, 400, 55, 55);

        // Set position/size of top/bottom buttons
        ON.setBounds(320, 50, 60, 50);
        OK.setBounds(482, 600, 60, 50);
        LCD.setBounds(150, 600, 300, 50);
        //prevents text within textbox being edited
        LCD.setEditable(false);
        display.setBounds(100, 600, 300, 50);

        // Create and add grid buttons
        for (int i = 0; i < 16 * 16; i++) 
        {
            buttons[i] = new GridButton(i % 16, (int) i / 16, this);
            grid.add(buttons[i]);
        }

        // Add L and R buttons to gui panel
        gui.add(L1);
        gui.add(L2);
        gui.add(L3);
        gui.add(L4);
        gui.add(R1);
        gui.add(R2);
        gui.add(R3);
        gui.add(R4);
        gui.add(ON);
        gui.add(OK);
        gui.add(LCD);
        gui.add(display);

        // Add grid to gui panel
        gui.add(grid);


        // Event handlers for ON  and OK
        ON.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                /*
                 *  If clicked in OnOfMode then switch to performance mode
                 *  Else set all buttons to have no colour
                 */
                if (SimoriOn.getInstance().getMode() instanceof OnOffMode) 
                {
                    SimoriOn.getInstance().setMode(new PerformanceMode());
                        clockHand = new ClockHand(SimoriOn.getInstance().getGui());
                        //start the clockhand movement
                        (new Thread(clockHand)).start();
                    ON.setBackground(Color.GREEN);
                }
                else 
                {
                	L1.setBackground(null);
                        L2.setBackground(null);
                        L3.setBackground(null);
                        L4.setBackground(null);
                    SimoriOn.getInstance().setMode(new OnOffMode());
                    clockHand.running.set(false);
                    ON.setBackground(null);
                }
                System.out.println("ON/OFF button clicked");
                for(GridButton button : buttons)
                {
                	button.turnOff();
                }
            }
        });

        OK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("OK button clicked");
            }
        });

        // Event handlers for L1-4
        L1.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("L1 button clicked");
                if (SimoriOn.getInstance().getMode() instanceof OnOffMode) 
                {
                	//If OnOffMode, then do nothing
                    return;
                } 
                else 
                {
                    SimoriOn.getInstance().setMode(new VoiceChangeMode());
                    L1.setBackground(Color.ORANGE);
                    R1.setBackground(null);
                    R2.setBackground(null);
                    R3.setBackground(null);
                    R4.setBackground(null);
                    L2.setBackground(null);
                    L3.setBackground(null);
                    L4.setBackground(null);
                    for(GridButton button : buttons)
                    {
                    	button.turnOff();
                    }
                }
            }
        });

        L2.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("L2 button clicked");
            
            if (SimoriOn.getInstance().getMode() instanceof OnOffMode) 
                {
                	//If OnOffMode, then do nothing
                    return;
                } 
                else 
                {
                    SimoriOn.getInstance().setMode(new VoiceChangeMode());
                    L2.setBackground(Color.ORANGE);
                    R1.setBackground(null);
                    R2.setBackground(null);
                    R3.setBackground(null);
                    R4.setBackground(null);
                    L1.setBackground(null);
                    L3.setBackground(null);
                    L4.setBackground(null);
                    for(GridButton button : buttons)
                    {
                    	button.turnOff();
                    }
                }
            }
        });

        L3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("L3 button clicked");
            
            if (SimoriOn.getInstance().getMode() instanceof OnOffMode) 
                {
                	//If OnOffMode, then do nothing
                    return;
                } 
                else 
                {
                    SimoriOn.getInstance().setMode(new VoiceChangeMode());
                    L3.setBackground(Color.ORANGE);
                    R1.setBackground(null);
                    R2.setBackground(null);
                    R3.setBackground(null);
                    R4.setBackground(null);
                    L1.setBackground(null);
                    L2.setBackground(null);
                    L4.setBackground(null);
                    for(GridButton button : buttons)
                    {
                    	button.turnOff();
                    }
                }
            }
            
        });

        L4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("L4 button clicked");
            
            if (SimoriOn.getInstance().getMode() instanceof OnOffMode) 
                {
                	//If OnOffMode, then do nothing
                    return;
                } 
                else 
                {
                    SimoriOn.getInstance().setMode(new VoiceChangeMode());
                    L4.setBackground(Color.ORANGE);
                    R1.setBackground(null);
                    R2.setBackground(null);
                    R3.setBackground(null);
                    R4.setBackground(null);
                    L1.setBackground(null);
                    L2.setBackground(null);
                    L3.setBackground(null);
                    for(GridButton button : buttons)
                    {
                    	button.turnOff();
                    }
                }
            }
        });

        // Event handlers for R1-4
        R1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("R1 button clicked");
            if (SimoriOn.getInstance().getMode() instanceof OnOffMode) 
                {
                	//If OnOffMode, then do nothing
                    return;
                } 
                else 
                {
                    SimoriOn.getInstance().setMode(new VoiceChangeMode());
                    R1.setBackground(Color.ORANGE);
                    R2.setBackground(null);
                    R3.setBackground(null);
                    R4.setBackground(null);
                    L1.setBackground(null);
                    L2.setBackground(null);
                    L3.setBackground(null);
                    L4.setBackground(null);
                    for(GridButton button : buttons)
                    {
                    	button.turnOff();
                    }
                }
            }
        });

        R2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("R2 button clicked");
            if (SimoriOn.getInstance().getMode() instanceof OnOffMode) 
                {
                	//If OnOffMode, then do nothing
                    return;
                } 
                else 
                {
                    SimoriOn.getInstance().setMode(new VoiceChangeMode());
                    R2.setBackground(Color.ORANGE);
                    R1.setBackground(null);
                    R3.setBackground(null);
                    R4.setBackground(null);
                    L1.setBackground(null);
                    L2.setBackground(null);
                    L3.setBackground(null);
                    L4.setBackground(null);
                    for(GridButton button : buttons)
                    {
                    	button.turnOff();
                    }
                }
            }
        });

        R3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("R3 button clicked");
                
            if (SimoriOn.getInstance().getMode() instanceof OnOffMode) 
                {
                	//If OnOffMode, then do nothing
                    return;
                } 
                else 
                {
                    SimoriOn.getInstance().setMode(new VoiceChangeMode());
                    R3.setBackground(Color.ORANGE);
                    R1.setBackground(null);
                    R2.setBackground(null);
                    R4.setBackground(null);
                    L1.setBackground(null);
                    L2.setBackground(null);
                    L3.setBackground(null);
                    L4.setBackground(null);
                    for(GridButton button : buttons)
                    {
                    	button.turnOff();
                    }
                }
            }
        });

        R4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("R4 button clicked");
            
            if (SimoriOn.getInstance().getMode() instanceof OnOffMode) 
                {
                	//If OnOffMode, then do nothing
                    return;
                } 
                else 
                {
                    SimoriOn.getInstance().setMode(new VoiceChangeMode());
                    R4.setBackground(Color.ORANGE);
                    R1.setBackground(null);
                    R2.setBackground(null);
                    R3.setBackground(null);
                    L1.setBackground(null);
                    L2.setBackground(null);
                    L3.setBackground(null);
                    L4.setBackground(null);
                    for(GridButton button : buttons)
                    {
                    	button.turnOff();
                    }
                }
            }    
        });

    }

    /**
     * get method to return the GUI that will be used as
     * the grid
     *
     * @return gui that will be used for the entire UI.
     */
    public JPanel getGui() 
    {
        return this.gui;
    }

   /**
    * method that gets the button coordinates from the grid
    * @param x the x coordinates of a button
    * @param y the y coordinates of a button
    * @return the buttons at different positions
    */
    public GridButton getButton(int x, int y) 
    {
        return buttons[y * 16 + x];
    }
    
    /**
     * method that will highlight each column and row using the
     * coordinates system
     * @param x x coordinate of the box
     * @param y y coordinate of the box
     */
    public void highlightColumnAndRow(int x, int y) 
    {
        // Turn off all buttons
        for (int i = 0; i < this.buttons.length; i++) {
            buttons[i].turnOff();
        }
        // Highlight buttons in the same row and column
        for (int i = 0; i < 16; i++) {
            getButton(x, i).turnOn();
            getButton(i, y).turnOn();
        }
    }
    
    /**
     * method that will only highlight the column of the grid 
     * this can be used for the oscillation of the clockhand too. 
     * @param x
     */
    public void highlightColumn(int x)
    {

        // Turn off all buttons
        for (GridButton button : buttons) {
            if(!(GridButton.getButtonsSelected().contains(button))) {
                button.turnOff();
            }
        }

        // Highlight every 5 buttons in the same column
        for (int i=0; i < 16; i+=5) {
            getButton(x, i).turnOn();
        }

    }

   /**
    * method that creates the main GUI that will be used to represent
    * the grid being used. this method will return the GUI being used
    * @return g which is the instance of the GUI being used
    */
    public static GUI makeGUI()
    {
        final GUI g = new GUI();

        //run the GUI within its own thread and not in main thread. 
        Runnable runnable = new Runnable() {

            public void run() {
                JFrame frame = new JFrame("Simori-ON");
                //set location in center of screen
                frame.setLocation(400, 100);
                frame.setPreferredSize(new Dimension(700, 690));
                frame.setResizable(false);

                //set screen size to adapt to different screen dimensions
                //should stay in centre of screen when executed on all 
                //screens.
                Toolkit screen = Toolkit.getDefaultToolkit();
                Dimension screenSize = screen.getScreenSize();
                int screenWidth = screenSize.width;
                int screenHeight = screenSize.height;
                frame.setLocation(screenWidth / 4, screenHeight / 8);
                // Add gui panel to JFrame
                frame.add(g.getGui());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(runnable);
        return g;
    }
}
