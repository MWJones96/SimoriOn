package simori;

import java.awt.Color;

public class PerformanceMode implements Mode {

    public PerformanceMode() {
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
        }
        else
        {
        	button.turnOff();
        }
    }

}
