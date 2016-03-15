package simori.button;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import simori.SimoriOn;
import simori.mode.OnOffMode;
import simori.mode.PerformanceMode;

public class ONButton extends FunctionButton
{
	private static final long serialVersionUID = 1L;
	
	public ONButton()
	{
		super();
		onState = new ImageIcon(new ImageIcon("res/ButtonOnON.png")
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		offState = new ImageIcon(new ImageIcon("res/ButtonOffON.png")
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SimoriOn.getInstance().getMode() instanceof OnOffMode) {
					ONButton.this.turnOn();
					SimoriOn.getInstance().setMode(new PerformanceMode());
				} else {
					ONButton.this.turnOff();
					SimoriOn.getInstance().setMode(new OnOffMode());
					SimoriOn.getInstance().resetDevice();
				}
			}
		});
	}

}