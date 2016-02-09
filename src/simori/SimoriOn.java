package simori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimoriOn
{
    // Self instance (Singleton pattern)
    private static SimoriOn instance = null;
    
    //The current mode
    private Mode mode;
    //The speed of the metronome (in beats per minute)
    private int bpm;
    private Layer[] layers;
    
    protected SimoriOn()
    {
    	mode = new OnOffMode(this);
    	bpm = 60; //TEMP
    	layers = new Layer[16];
    	GUI.makeGUI();
    }
    
    public static SimoriOn getInstance(){
        if(instance == null){
            instance = new SimoriOn();
        }
        return instance;
    }
    
    public void setMode(Mode mode)
    {
        this.mode = mode;
    }
    
    public void setBPM(int bpm)
    {
    	this.bpm = bpm;
    }

    public Mode getMode(){
        return mode;
    }
}