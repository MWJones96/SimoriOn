package simori.mode;

/**
 * @author Team G
 */
public class MasterSlaveDispatcher implements Runnable {
    public void run(){

        // Create master and slave objects
        Master master = new Master();
        Slave slave = new Slave();

        // Create threads
        Thread masterThread = new Thread(master);
        Thread slaveThread = new Thread(slave);

        // Start the slave thread (waits for master)
        slaveThread.start();

        // Pause execution until slave thread is done
        try {
            slaveThread.join();
        } catch (InterruptedException e){
        }

        // If slave is unsuccessful in connecting to a master,
        // start the master thread which waits for a slave.
        if (!slave.getSuccess()){
            masterThread.start();
        }
    }
}
