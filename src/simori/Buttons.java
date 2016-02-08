package simori;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

class Buttons extends JButton implements ActionListener{
    
    private int x;
    private int y;
    private GUI gui;
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
                System.out.println("Button clicked; co-ords: " + x + ", " + y);
                gui.getButton(x, y).setBackground(Color.ORANGE);
            }
    	}
    	);
    }
    
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Button pressed: co-ords: " + x + ", " + y);
	}
    
    public int getCoordsX()
    {
        return this.x;
    }
    
    public int getCoordsY()
    {
        return this.y;
    }
}