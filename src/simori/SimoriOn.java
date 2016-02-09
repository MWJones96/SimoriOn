package simori;

public class SimoriOn
{
    // Self instance (Singleton pattern)
    private static SimoriOn instance = null;
    
    //The current mode
    private Mode mode;
    //The speed of the metronome (in beats per minute)
    private int bpm;
    private Layer[] layers;
    private GUI gui;
    
    protected SimoriOn()
    {
    	mode = new OnOffMode();
    	bpm = 60; //TEMP
    	layers = new Layer[16];
    	gui = GUI.makeGUI();
    }
    
    public static SimoriOn getInstance()
    {
        // Singleton pattern..
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

    public Mode getMode()
    {
        return mode;
    }

    public GUI getGui()
    {
        return gui;
    }
}