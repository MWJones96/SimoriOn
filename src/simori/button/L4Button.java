package simori.button;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import simori.core.SimoriOn;
import simori.mode.LoopPointChangeMode;
import simori.mode.OnOffMode;
/**
 * class that is used to represent that button for when L4 is pressed. 
 * this class is a child class from the FunctionButton Parent class. 
 * this class represents the button and its functionalities for L4 to 
 * encapsulate it more. 
 * @author team G
 *
 */
public class L4Button extends FunctionButton
{
	private static final long serialVersionUID = 1L;

	public L4Button()
	{
		super();
		onState = new ImageIcon(new ImageIcon("res/ButtonOnL4.png")
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		offState = new ImageIcon(new ImageIcon("res/ButtonOffL4.png")
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {}
				else if (!state) {
					state = true;
					SimoriOn.getInstance().getGui().turnOffFunctionButtons();
					SimoriOn.getInstance().getGui().turnOffGridButtons();
					SimoriOn.getInstance().setMode(new LoopPointChangeMode());
					turnOn();
				}
			}
		});
	}

}