package simori;

public class SimoriOn 
{
	//The current mode
    private Mode mode;
    //The speed of the metronome (in beats per minute)
    private int bpm;
    private Layer[] layers;
    
    public SimoriOn()
    {
    	mode = new OnOffMode(this);
    	bpm = 60; //TEMP
    	layers = new Layer[16];
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