package simori;

import java.util.concurrent.atomic.AtomicBoolean;

/**ClockHand thread class - the object is created when
 * the ON/OFF button is clicked while in ON/OFF mode.
 * Highlights the columns in sequence and sleeps
 * for a fixed amount of time between columns.
 *
 * @author 640025919
 * @date 09/02/2016.
 */

public class ClockHand implements Runnable {

    private GUI gui;
    private int loopPoint = 16;
    public AtomicBoolean running = new AtomicBoolean();

    public ClockHand (GUI gui){
        this.gui = gui;
    }

    public void run() {
        System.out.println("Clockhand thread started");
        running.set(true);

        while(running.get()) {

            for (int i=0; i<loopPoint; i++){
                gui.highlightColumn(i);

                // Iterate through all selected buttons
                for(int j=0; j<GridButton.getButtonsSelected().size(); j++)
                {
                    GridButton button = GridButton.getButtonsSelected().get(j);

                    // If selected button is in the current column, play sound
                    if (button.getCoordsX() == i)
                    {
                        // Sound test
                        (new Thread(new Sounds(50 - button.getCoordsY(), 64))).start();

                    }
                }

                if (!running.get())
                {
                    for(GridButton button : gui.buttons)
                    {
                        button.turnOff();
                    }
                    break;
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
