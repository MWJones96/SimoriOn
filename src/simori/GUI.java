package simori;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * 
 * @author 640032165, 640025919
 * @version 1.0
 * @since 05-02-2016
 * GUI class that creates basic Layout for Sprint 1. Class  creates main 
 * board for Simori-ON and simulates all buttons, but does not include
 * full functionailty for Sprint 1. Class uses the Runnable interface
 * to allow GUI to run within its own thread. 
 * Buttons include: 4 buttons for the left, 4 buttons for the right
 * ON/OFF button and 16x16 grid buttons.
 * Class outline by 640025919, main functionality by 640032165
 */
public class GUI{

    private JPanel gui  = new JPanel();
    private JPanel grid = new JPanel();
    //uneditable label
    JLabel display = new JLabel("Action:");
    //empty text box with font-size : 15
    JTextField LCD = new JTextField(15);
    
    
    

    // Left buttons
    JButton L1  = new  JButton("L1");
    JButton L2  = new  JButton("L2");
    JButton L3  = new  JButton("L3");
    JButton L4  = new  JButton("L4");

    // Right buttons
    JButton R1  = new  JButton("R1");
    JButton R2  = new  JButton("R2");
    JButton R3  = new  JButton("R3");
    JButton R4  = new  JButton("R4");
    
    //ON/OFF button
    JButton ON = new JButton("ON");
    JButton OFF = new JButton("OFF");
    
    //OK button
    JButton OK = new JButton("OK");
    

    // Array for 16x16 grid buttons
    Buttons buttons[] = new Buttons[256];

	/**
	 * Constructor for GUI without any parameters. It is used in order to 
	 * create the default style of how the Simori-ON board will look. 
	 * sets height and width and location of buttons and creates default
	 * grid to be used for the Simori-ON. 
	 * @return void - contructor sets default values so
	 *  nothing is returned.  
	 */
    public GUI() {
    	//set default values for the grid and grid dimensions
    	gui.setLayout(null);
        grid.setLayout (new GridLayout(16,16));
        grid.setBounds(100,100,500,500);

        // Set position/size of left buttons
        L1.setBounds(10,100,50,50);
        L2.setBounds(10,200,50,50);
        L3.setBounds(10,300,50,50);
        L4.setBounds(10,400,50,50);

        // Set position/size of right buttons
        R1.setBounds(640,100,50,50);
        R2.setBounds(640,200,50,50);
        R3.setBounds(640,300,50,50);
        R4.setBounds(640,400,50,50);
        
        // Set position/size of top/bottom buttons
        ON.setBounds(325,50,50,50);
        OFF.setBounds(325,50,50,50);
        OK.setBounds(487,600,50,50);
        LCD.setBounds(150,600,300,50);
        //prevents text within textbox being edited
        LCD.setEditable(false);
        display.setBounds(100, 600,300,50);

        // Create and add grid buttons
        for(int i = 0; i<256; i++){
            buttons[i] = new Buttons();
            grid.add(buttons[i]);
        }

        // Add L and R buttons to gui panel
        gui.add(L1); gui.add(L2); gui.add(L3); gui.add(L4);
        gui.add(R1); gui.add(R2); gui.add(R3); gui.add(R4);
        gui.add(ON); gui.add(OK); gui.add(LCD); gui.add(display);

        // Add grid to gui panel
        gui.add(grid);

    }
    /**
     * get method to return gui instance from JPanel. 
     * @return gui that will be used for the entire UI. 
     */
    public JPanel getGui() { 
    	return this.gui;
    	}

    public static void main(String[] argv){
        
        
        GUI g = new GUI(); 
        
        
        //run the GUI within its own thread and not in main thread. 
        Runnable runnable = new Runnable() {

            public void run() {
                JFrame frame = new JFrame("Simori-ON");
                //set location in center of screen
                frame.setLocation(400, 100);
                frame.setPreferredSize(new Dimension(700,670));
                frame.setResizable(false);

                //set screen size to adapt to different screen dimensions
                //should stay in centre of screen when executed on all 
                //screens.
                Toolkit screen = Toolkit.getDefaultToolkit();
                Dimension screenSize = screen.getScreenSize();
                int screenWidth = screenSize.width;
                int screenHeight = screenSize.height;
                frame.setLocation(screenWidth / 4, screenHeight/8);
                // Add gui panel to JFrame
                frame.add(g.getGui());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(runnable);
    }
}
