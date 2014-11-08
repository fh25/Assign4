package assign4;

import java.util.Scanner;

/**
 * Abstract class that represents a Console User Interface.
 */
public abstract class ConsoleUserInterface {

    /**
     * protected static dealership class reference
     */
	protected static Dealership dealership;
    
    /**
     * protected static scanner to be used for input.
     */
	protected static Scanner scanner = new Scanner(System.in);

	/**
	 * Abstract method. To be implemented by subclasses.
	 */
	protected abstract void printMenu();

    /**
     * Sets dealership
     * @param dealership 
     */
    public void setDealership(Dealership dealership) {
      ConsoleUserInterface.dealership = dealership;
    }

	/**
	 * Abstract method, to be overridden by subclasses. This method will run 
     * the main input/output interaction loops between the users and the system.
	 */
	public abstract void run();

}