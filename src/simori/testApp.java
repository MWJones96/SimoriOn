package simori;

public class testApp {
    
    public static void main(String[] args){
        
        SimoriOn simori = new SimoriOn();
        simori.setMode(new PerformanceMode(simori));
        simori.matrixButtonClicked();
        
        simori.setMode(new OnOffMode(simori));
        simori.matrixButtonClicked();
        
        simori.setMode(new VoiceChangeMode(simori));
        simori.matrixButtonClicked();
        
    }
    
}
