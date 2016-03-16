package simori.button;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import simori.core.SimoriOn;
import simori.mode.OnOffMode;
import simori.mode.VelocityChangeMode;

/**
 * class that is used to represent that button for when L2 is pressed. 
 * this class is a child class from the FunctionButton Parent class. 
 * this class represents the button and its functionalities for L2 to 
 * encapsulate it more. 
 * @author team G
 *
 */
public class L2Button extends FunctionButton
{
	private static final long serialVersionUID = 1L;

	public L2Button()
	{
		super();
		onState = new ImageIcon(new ImageIcon("res/ButtonOnL2.png")
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		offState = new ImageIcon(new ImageIcon("res/ButtonOffL2.png")
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimoriOn.getInstance().getSoundProcessor().killAllSound();
				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {}
				else if (!state) {
					state = true;
					SimoriOn.getInstance().getGui().turnOffFunctionButtons();
					SimoriOn.getInstance().getGui().turnOffGridButtons();
					SimoriOn.getInstance().setMode(new VelocityChangeMode());
					turnOn();
				}
			}
		});
	}

}