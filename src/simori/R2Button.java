package simori;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

/**
 * class that is used to represent that button for when R2 is pressed. 
 * this class is a child class from the FunctionButton Parent class. 
 * this class represents the button and its functionalities for R2 to 
 * encapsulate it more. 
 * @author team G
 *
 */
public class R2Button extends FunctionButton
{
	private static final long serialVersionUID = 1L;

	public R2Button()
	{
		super();
		onState = new ImageIcon(new ImageIcon("./res/ButtonOnR2.png")
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		offState = new ImageIcon(new ImageIcon("./res/ButtonOffR2.png")
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {}
				else if (!state) {
					state = true;
					SimoriOn.getInstance().getGui().turnOffFunctionButtons();
					SimoriOn.getInstance().getGui().turnOffGridButtons();
					SimoriOn.getInstance().setMode(new SaveConfigurationMode());
					turnOn();
				}
			}
		});
	}

}