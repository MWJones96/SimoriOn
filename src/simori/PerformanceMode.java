package simori;

import javax.sound.midi.Synthesizer;
import java.awt.Color;

public class PerformanceMode implements Mode {
		
    public PerformanceMode() {
        GridButton.clearButtonsSelected();
        if (SimoriOn.getClockHand() == null){
        	SimoriOn.setClockHand(new ClockHand(SimoriOn.getInstance().getGui()));
        	(new Thread(SimoriOn.getClockHand())).start();
        }
        else {
        	(new Thread(SimoriOn.getClockHand())).start();
        }
    }
    

    @Override
    public void processMatrixButton(GridButton button) 
    {
        // TODO Auto-generated method stub
        System.out.println("Matrix button processed in Performance Mode");
        System.out.println("Button clicked; co-ords: " + button.getCoordsX() + ", " + button.getCoordsY());
        if(!button.getState())
        {
        	button.turnOn();
            GridButton.addButtonsSelected(button);
        }
        else
        {
        	button.turnOff();
            GridButton.removeButtonsSelected(button);

        }
    }

}
