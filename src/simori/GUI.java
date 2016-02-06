package simori;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Dimension;

public class GUI {
    private JPanel gui = new JPanel();

    public GUI() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("GUI Test");
        panel.add(label);
        gui.add(panel);
    }

    public JPanel getGui() { return gui; }

    public static void main(String[] argv){
        GUI testGUI = new GUI();
        Runnable runnable = new Runnable() {
            public void run() {
                JFrame frame = new JFrame("GUI Test");
                frame.setPreferredSize(new Dimension(700,700));
                frame.add(testGUI.getGui());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(runnable);
    }
}
