package simori;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

/**The class that represents the buttons on the GUI
 * 
 * @author 8744
 * @date 09/02/2016
 */
class Buttons extends JButton
{
    //The X co-ord of the button
    private final int x;
    //The Y co-ord of the button
    private final int y;
    //The GUI the button is associated with
    private final GUI gui;
    //Whether the button is on or off
    private boolean on = false;
    
    public Buttons(int x, int y, GUI gui)
    {
    	this.x = x;
    	this.y = y;
    	this.gui = gui;
    	addMouseListener(new MouseAdapter()
    	{
            public void mouseClicked(MouseEvent e) 
            {
                /*System.out.println("Button clicked; co-ords: " + x + ", " + y);
                if(!on)
                {
                	gui.getButton(x, y).setBackground(Color.ORANGE);
                	on = true;
                }
                else
                {
                	gui.getButton(x, y).setBackground(null);
                	on = false;
                }*/
                Buttons buttonClicked = (Buttons)e.getSource();
                SimoriOn.getInstance().getMode().processMatrixButton(buttonClicked);
            }
    	}
    	);
    }

    public int getCoordsX()
    {
        return this.x;
    }
    
    public int getCoordsY()
    {
        return this.y;
    }
    
    public GUI getGUI()
    {
    	return this.gui;
    }
    
    public boolean getState()
    {
    	return this.on;
    }
    
}