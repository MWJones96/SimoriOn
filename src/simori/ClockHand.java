package simori;

/**
 * @author 640025919
 * @date 09/02/2016.
 */

public class ClockHand implements Runnable {

    private GUI gui;
    public boolean running = true;

    public ClockHand (GUI gui){
        this.gui = gui;
    }

    public void run() {
        System.out.println("Clockhand thread started");
        while(running) {
            for (int i=0; i<16; i++){
                gui.highlightColumn(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
