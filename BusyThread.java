package assign4;

/**
 * BusyThread class that implements concurrency by printing out '.' to 
 * simulate a progress bar.
 * @author fhj
 */
public class BusyThread extends Thread {

    /**
     * private boolean busy variable
     */
	private boolean busy = true;

    /**
     * Override the run method in order to invoke the thread.
     */
    @Override
	public void run() {
        System.out.print("\nWaiting for response");
        while(busy) {
          try {
            System.out.printf(".");
            Thread.sleep(500);
          } catch (InterruptedException ex) {
            System.err.print("Interrupted.");
          }
        }
	}

    /**
     * This method toggles the variable busy to stop the execution.
     * @param b 
     */
    void setBusy(boolean b) {
        this.busy = b;
    }

}