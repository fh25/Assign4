package assign4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Dealership class that implements Serializable and acts as the hub for the
 * dealership program.
 * @author fhj
 */
public class Dealership implements Serializable {
  
    /**
     * private static counter.
     */
    private static int userIdCounter = 0;
    
    /**
     * private static logged for logging messages to the console and to a file.
     */
    private static Logger logged = Logger.getLogger("Dealership Class");

    /**
     * private class reference cui.
     */
	private ConsoleUserInterface cui;
    
    /**
     * private class reference userRecords.
     */
	private UserRecords userRecords;
    
    /**
     * private class reference transRec.
     */
	private TransRecords transRec;
    
    /**
     * private class reference vehInv.
     */
	private VehicleInventory vehInv;
    
    /**
     * private class reference commMgr.
     */
    private CommunicationMngr commMgr;
    
    /**
     * Default constructor that initializes the private class references.
     */
    Dealership() {
        this.cui = new GeneralView(this);
        this.userRecords = new UserRecords();
        this.transRec = new TransRecords(this);
        this.vehInv = new VehicleInventory();
        this.commMgr = new CommunicationMngr();
    }
    
    /**
     * Returns the userRecords.
     * @return 
     */
    public UserRecords getUserRecords() {
      return userRecords;
    }
    
    /**
     * Returns transRec.
     * @return 
     */
    public TransRecords getTransRec() {
      return transRec;
    }
    
    /**
     * Returns vehInv.
     * @return 
     */
    public VehicleInventory getVehInv() {
        return vehInv;        
    }

    /**
     * Returns commMgr.
     * @return 
     */
    public CommunicationMngr getCommMgr() {
      return commMgr;
    }
    
    /**
     * This method is used to read the database from a file, as a serializable object.
     * A thread will be created to print the waiting for response.
     * @return The Dealership serialized object.
     * @throws java.lang.InterruptedException
     * @throws java.io.FileNotFoundException
     */
    public static Dealership readDatabase() throws InterruptedException, FileNotFoundException, IOException {
       
        System.out.print("\nReading database...");
        BusyThread busy = new BusyThread();
        busy.start();
        
        Thread.sleep(1500);
      
        Dealership data = null;

        // Try to read existing dealership database from a file
        InputStream file = null;
        InputStream buffer = null;
        ObjectInput input = null;
        try {
            file = new FileInputStream("Dealership.ser");
            buffer = new BufferedInputStream(file);
            input = new ObjectInputStream(buffer);
                
            data = (Dealership) input.readObject();
            input.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.toString());
            Logger.getLogger(Dealership.class.getName());
            logged.addHandler(new FileHandler("logs.txt"));
        } catch (FileNotFoundException ex) {
            System.err.println("Database file not found.");
        } catch (IOException ex) {
            System.err.println(ex.toString());
        } finally {
            close(file);
        }
        busy.setBusy(false);
        System.out.println("Done.");
        
        return data;
    }
    
    /**
     * This method is used to save the Dealership database as a serializable
     * object. A thread will be created to print the waiting for response.
     * @param data
     * @throws java.lang.InterruptedException
     */
     public static void writeDatabase(Dealership data) throws InterruptedException {
        
       System.out.print("\nWriting database...");
        BusyThread busy = new BusyThread();
        busy.start();
        
        Thread.sleep(1500);
        
        //serialize the database
        OutputStream file = null;
        OutputStream buffer = null;
        ObjectOutput output = null;
        try {
            file = new FileOutputStream("Dealership.ser");
            buffer = new BufferedOutputStream(file);
            output = new ObjectOutputStream(buffer);
            output.writeObject(data);
            output.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        } finally {
            close(file);
        }
        busy.setBusy(false);
        System.out.println("Done.");
    }
    
    /**
     * Auxiliary convenience method used to close a file and handle possible
     * exceptions that may occur.
     * @param f
     */
    private static void close(Closeable f) {
        if (f == null) {
            return;
        }
        try {
            f.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }   
    }
    
	/**
	 * 
	 * @param args
     * @throws java.lang.InterruptedException
     * @throws java.io.FileNotFoundException
	 */
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException { 
        // If you could not read from the file, create a new database.
        Dealership data = readDatabase();
        
        if (data == null) {
            System.out.println("New database created.");
            data = new Dealership();
        }
        
        data.getUserRecords().addAdmin("Admin", 80000, 127001, true, 0000);
        data.cui.setDealership(data);
        data.cui.run();
        writeDatabase(data);
	}
}