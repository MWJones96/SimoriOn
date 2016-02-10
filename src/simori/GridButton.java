package simori;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

/**class that represents the grid buttons on the GUI. the grid made 
 * uses a coordinate system made up of x rows and y columns. the top left 
 * box has the coordinate (0,0) and the bottom right box with (15,15). 
 * 
 * @author Alex Sahin, Carl Saptarshi, Dennis Luo, Matt Jones, G
 * @date 09/02/2016
 */
class GridButton extends JButton
{
	private static final long serialVersionUID = 1L;
	//The X and Y co-ord of the button
    private final int x;
    private final int y;
    
    //The GUI the button is associated with
    private final GUI gui;
    //Whether the button is on or off
    private boolean on = false;
    // Contains all buttons that are currently selected on the grid
    private static ArrayList<GridButton> buttonsSelected = new ArrayList<GridButton>();


    /**
     * constructor that is used that takes into account 3 arguments. 
     * performs actions based on the location of the mouse click on the 
     * grid GUI. 
     * @param x is the x coordinate for each box
     * @param y is the y coordinate for each box
     * @param gui is the interface where the grid will be placed. 
     */
    public GridButton(int x, int y, GUI gui)
    {
    	this.x = x;
    	this.y = y;
    	this.gui = gui;
        
    	//compatable with other OS's.
        this.setOpaque(true);
        
        /*
         * when the button is clicked, the button clicked will be
         * selected accordingly.
         */
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
    /**
     * method that will return the button that has been selected in 
     * the grid
     * @return buttonSelected 
     */
    public static ArrayList<GridButton> getButtonsSelected()
    {
        return buttonsSelected;
    }
    
    /**
     * method that is used whilst clicking. when a button is cliked, it 
     * should stay highlighted. 
     * @param b the button to be slected
     */
    public static void addButtonsSelected(GridButton b){
        buttonsSelected.add(b);
    }

    /**
     * method that is used whilst clicking. when a button is cliked, it 
     * should the colour will reutrn to default state. 
     * @param b the button to be deslected
     */
    public static void removeButtonsSelected(GridButton b){
        buttonsSelected.remove(b);
    }

    /**
     * method that will clear all the buttons on the grid so that the 
     * buttons can be pressed for a different mode again.
     *  
     */
    public static void clearButtonsSelected(){
        buttonsSelected.clear();
    }

    /**
     * method that retreives the x coordinates for button selected
     * and returns the coordinate
     * @return x the x coordinate of button clicked 
     */
    public int getCoordsX()
    {
        return this.x;
    }
    
    /**
     * method that retreives the y coordinates for button selected
     * and returns the coordinate
     * 
     * @return y the y coordinate of button clicked 
     */
    public int getCoordsY()
    {
        return this.y;
    }
    
    /**method that retreives the gui object and returns the gui object
     * 
     * @return gui the interface object launched 
     */
    public GUI getGUI()
    {
    	return this.gui;
    }
    
    /**method that retreives the current state of the GUI 
     * (either on/off) and returns the state 
     * 
     * @return on the state of the GUI will be on/off
     */
    public boolean getState()
    {
    	return this.on;
    }
    
    /**Turns on the GUI button that was selected
     * and sets its colour to orange and its
     * state to on
     */
    public void turnOn()
    {
    	if(!on)
    	{
    		this.setBackground(Color.ORANGE);
        	this.setOpaque(true);
        	this.setBorderPainted(true);
        	on = true;
    	}
    }
    
    /**Turns off the GUI button that was selected
     * and sets its colour to null (gray) and its
     * state to off
     */
    public void turnOff()
    {
    	if(on)
    	{
    		this.setBackground(null);
    		this.setBorderPainted(true);
    		this.setOpaque(false);
    		on = false;
    	}
    }
    
}