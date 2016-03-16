package simori.button;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import simori.core.SimoriOn;
import simori.mode.OnOffMode;
import simori.mode.PerformanceMode;

public class OKButton extends FunctionButton
{
	private static final long serialVersionUID = 1L;
	
	public OKButton()
	{
		super();
		onState = new ImageIcon(new ImageIcon("res/ButtonOnOK.png")
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		offState = new ImageIcon(new ImageIcon("res/ButtonOffOK.png")
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(SimoriOn.getInstance().getMode() instanceof PerformanceMode))
					SimoriOn.getInstance().getSoundProcessor().killAllSound();
					
				SimoriOn.getInstance().getMode().processOKButton();

				// Implements button flash as a thread, so that other processes
				// won't be stopped for 250ms
				new Thread()
				{
					@Override
					public void run() {
						//If ON/OFF, then return
						if(SimoriOn.getInstance().getMode() instanceof OnOffMode){return;}
						
						SimoriOn.getInstance().getGui().writeLayerToGUI(SimoriOn.getInstance()
								.getCurrentLayer());
						OKButton.this.setIcon(new ImageIcon(new ImageIcon(
								"./res/ButtonOnOK.png").getImage()
								.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
						try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						OKButton.this.setIcon(new ImageIcon(new ImageIcon(
								"./res/ButtonOffOK.png").getImage()
								.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					}

				}.start();

			}

		});
	}

}