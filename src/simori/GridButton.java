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
class GridButton extends JButton
{
	private static final long serialVersionUID = 1L;
	//The X co-ord of the button
    private final int x;
    //The Y co-ord of the button
    private final int y;
    //The GUI the button is associated with
    private final GUI gui;
    //Whether the button is on or off
    private boolean on = false;
    
    public GridButton(int x, int y, GUI gui)
    {
    	this.x = x;
    	this.y = y;
    	this.gui = gui;
        
        this.setOpaque(true);
        
    	addMouseListener(new MouseAdapter()
    	{
            public void mouseClicked(MouseEvent e) 
            {
                GridButton buttonClicked = (GridButton)e.getSource();
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
    
    public void turnOn()
    {
        this.setBackground(Color.ORANGE);
        this.setOpaque(true);
        this.setBorderPainted(false);
        on = true;
    }
    
    public void turnOff()
    {
        this.setBackground(null);
        this.setBorderPainted(true);
        this.setOpaque(false);
        on = false;
    }
    
}