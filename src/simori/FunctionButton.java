package simori;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * this method is used as part of the GUI to return the buttons as 
 * toggle buttons rather than JButtons. we have used .png files to simulate
 * the left hand L1-3 an R1-3 buttons as well for the on-off buttons that 
 * are associated with the state of the button for example if L1 is clicked
 * then L1 will turn orange and when clicked again will be turned off and 
 * go back to the default colour, grey.
 * @author Matthew Jones (Carl Saptarshi)
 *
 */
public class FunctionButton extends JButton
{
	private ImageIcon onState;
	private ImageIcon offState;
	
	private String buttonName;
	
	//Off by default
	private boolean state = false;
	
	/**
	 * constructor that will take the default state of the functional button
	 * that is - before the button has been clicked, give it the default state. 
	 * @param name
	 */
	public FunctionButton(String name)
	{
		this.buttonName = name;
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		
		//CANNOT USE SWITCH STATEMENT FOR STRINGS, AS IS JAVA 7 FEATURE
		if(name == "L1")
		{
			onState = new ImageIcon(new ImageIcon("./res/ButtonOnL1.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			offState = new ImageIcon(new ImageIcon("./res/ButtonOffL1.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		}
		else if(name == "L2")
		{
			onState = new ImageIcon(new ImageIcon("./res/ButtonOnL2.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			offState = new ImageIcon(new ImageIcon("./res/ButtonOffL2.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		}
		else if(name == "L3")
		{
			onState = new ImageIcon(new ImageIcon("./res/ButtonOnL3.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			offState = new ImageIcon(new ImageIcon("./res/ButtonOffL3.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		}
		else if(name == "L4")
		{
			onState = new ImageIcon(new ImageIcon("./res/ButtonOnL4.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			offState = new ImageIcon(new ImageIcon("./res/ButtonOffL4.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		}
		else if(name == "R1")
		{
			onState = new ImageIcon(new ImageIcon("./res/ButtonOnR1.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			offState = new ImageIcon(new ImageIcon("./res/ButtonOffR1.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		}
		else if(name == "R2")
		{
			onState = new ImageIcon(new ImageIcon("./res/ButtonOnR2.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			offState = new ImageIcon(new ImageIcon("./res/ButtonOffR2.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		}
		else if(name == "R3")
		{
			onState = new ImageIcon(new ImageIcon("./res/ButtonOnR3.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			offState = new ImageIcon(new ImageIcon("./res/ButtonOffR3.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		}
		else if(name == "R4")
		{
			onState = new ImageIcon(new ImageIcon("./res/ButtonOnR4.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			offState = new ImageIcon(new ImageIcon("./res/ButtonOffR4.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		}

		this.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(SimoriOn.getInstance().getMode() instanceof OnOffMode)
				{}
				else if(!state)
				{
					state = true;
					SimoriOn.getInstance().getGui().turnOffFunctionButtons();
					SimoriOn.getInstance().getGui().turnOffGridButtons();
					turnOn();
					if(buttonName == "L1")
						SimoriOn.getInstance().setMode(new VoiceChangeMode());
					if(buttonName == "L2")
						SimoriOn.getInstance().setMode(new VelocityChangeMode());
					if(buttonName == "L3")
						SimoriOn.getInstance().setMode(new LoopSpeedChangeMode());
					if(buttonName == "L4")
						SimoriOn.getInstance().setMode(new LoopPointChangeMode());
					if(buttonName == "R1")
						SimoriOn.getInstance().setMode(new LayerChangeMode());
					if(buttonName == "R2")
						SimoriOn.getInstance().setMode(new SaveConfigurationMode());
					if(buttonName == "R3")
						SimoriOn.getInstance().setMode(new LoadConfigurationMode());
					if(buttonName == "R4")
						SimoriOn.getInstance().setMode(new MasterSlaveMode());
				}
			}	
		});
	}
	/**
	 * set the icon to on, which is done by changing the colours from grey 
	 * to orange. 
	 */
	public void turnOn()
	{
		state = true;
		this.setIcon(onState);
	}
	/**
	 * set the icon to off, which is done by changing the colours from
	 *  orange to grey
	 */
	public void turnOff()
	{
		state = false;
		this.setIcon(offState);
	}
	/**
	 * method that will get the state of the button that has either been
	 * pressed or not. 
	 * @return
	 */
	public boolean getState()
	{
		return this.state;
	}
}