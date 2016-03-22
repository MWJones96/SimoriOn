package simori.button;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import simori.core.SimoriOn;
import simori.mode.LoadConfigurationMode;
import simori.mode.OnOffMode;
import simori.mode.PerformanceMode;

/**
 * class that is used to represent that button for when R3 is pressed. 
 * this class is a child class from the FunctionButton Parent class. 
 * this class represents the button and its functionalities for R3 to 
 * encapsulate it more. 
 * @author team G
 *
 */
public class R3Button extends FunctionButton
{
	private static final long serialVersionUID = 1L;

	public R3Button()
	{
		super();
		onState = new ImageIcon(new ImageIcon("res/ButtonOnR3.png")
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		offState = new ImageIcon(new ImageIcon("res/ButtonOffR3.png")
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimoriOn.getInstance().getSoundProcessor().killAllSound();
				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {}
				else if (!state) {
					state = true;
					SimoriOn.getInstance().getGui().turnOffFunctionButtons();
					SimoriOn.getInstance().getGui().turnOffGridButtons();
					SimoriOn.getInstance().setMode(new LoadConfigurationMode());
					turnOn();
				}
				else if(state)
				{
					SimoriOn.getInstance().setMode(new PerformanceMode());
					turnOff();
				}
			}
		});
	}

}