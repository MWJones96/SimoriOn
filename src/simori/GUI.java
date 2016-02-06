package simori;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Dimension;

public class GUI {

    private JPanel gui = new JPanel();
    private JPanel grid = new JPanel();

    // Left buttons
    JButton L1  = new  JButton("L1");
    JButton L2 = new  JButton("L2");
    JButton L3 = new  JButton("L3");
    JButton L4  = new  JButton("L4");

    // Right buttons
    JButton R1  = new  JButton("R1");
    JButton R2 = new  JButton("R2");
    JButton R3 = new  JButton("R3");
    JButton R4  = new  JButton("R4");

    // Array for 16x16 grid buttons
    Buttons buttons[] = new Buttons[256];


    public GUI() {

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

        // Create and add grid buttons
        for(int i = 0; i<256; i++){
            buttons[i] = new Buttons();
            grid.add(buttons[i]);
        }

        // Add L and R buttons to gui panel
        gui.add(L1); gui.add(L2); gui.add(L3); gui.add(L4);
        gui.add(R1); gui.add(R2); gui.add(R3); gui.add(R4);

        // Add grid to gui panel
        gui.add(grid);

    }

    public JPanel getGui() { return gui; }

    public static void main(String[] argv){
        
        // Create GUI instance
        GUI g = new GUI();
        Runnable runnable = new Runnable() {

            public void run() {
                JFrame frame = new JFrame("Basic Grid");
                frame.setPreferredSize(new Dimension(700,700));
                frame.setResizable(false);

                // Add gui panel to JFrame
                frame.add(g.getGui());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(runnable);
    }
}
