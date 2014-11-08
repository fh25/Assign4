package assign4;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The customer console user interface.
 */
public class CustomerView extends ConsoleUserInterface {
  
    /**
     * Private logged variable to log messages to the console and to a file.
     */
    private static final Logger logged = Logger.getLogger(CustomerView.class.getName());

    /**
     * Overrides printMenu.
     */
    @Override
	protected void printMenu() {
        System.out.println(
          "\nCustomer View\nChoose one of the following.\n"      
          + "1. Search Inventory.\n "
          + "2. Send a Message to Dealership.\n"
          + "3. Exit program.");
	}

    /**
     * Overrides run method.
     */
    @Override
	public void run() {
        int choice = 0;
        Boolean exit = false;
        do {
          printMenu();
          try {
              System.out.print("Enter choice: ");
              choice = scanner.nextInt();
              
              switch (choice) {
                  case 1: searchInventory(); break;
                  case 2: sendMsg(); break;
                  case 3: exit = true; break;
                  default: System.err.println("Please select a number between"
                      + "1 and 3.");
              }
          } catch (InputMismatchException ex) {
                System.err.println("Input not valid. Please Try again.");
                Logger.getLogger(CustomerView.class.getName()).log(Level.WARNING, 
                    "Input is not of type int!");
            try {
              logged.addHandler(new FileHandler("logs.%u.%g.txt"));
            } catch (IOException ex1) {
              Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
              Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex1);
            }
                scanner.nextLine();
                continue;
          } catch (BadInputException ex) {
            Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(CustomerView.class.getName()).log(Level.WARNING, 
                    "Input is not of type int!");
            scanner.nextLine();
          } catch (InterruptedException ex) {
            Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex);
            try {
              logged.addHandler(new FileHandler("logs.%u.%g.txt"));
            } catch (IOException ex1) {
              Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
              Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex1);
            }
            scanner.nextLine();
          }
        } while (!exit);
	}

	/**
	 * Input/Output method that allows a customer to search the vehicle 
     * inventory by specifying the vehicle characteristics. The method only 
     * handles the user console input/output. To perform the actual search 
     * operation, it calls other methods of the program.
	 */
	private void searchInventory() throws BadInputException, InterruptedException {
		// TODO - implement CustomerView.searchInventory
      
        System.out.println("\nSelect search type:\n"
            + "1. VIN Number\n"
            + "2. Price Range\n"
            + "3. Exit\n");
        System.out.print("Your choice: ");
        int selection = scanner.nextInt();
        if (selection < 1 || selection > 3)
            throw new BadInputException("Invalid search selection. Choose 1, 2, or 3");
        if (selection == 3)
            return;
        scanner.nextLine();
        
        if (selection == 1) {
            System.out.print("\nEnter vehicle VIN number (string): ");
            String vin = scanner.nextLine();
            if (vin.length() > 10)
                throw new BadInputException("VIN should not be more that 10 "
                + "characters long.");
            
            dealership.getVehInv().searchInventory(vin);
            
        } else if (selection == 2) {
            System.out.print("\nEnter low price amount (float): ");
            float lowValue = scanner.nextFloat();
            if (lowValue < 0.0f)
                throw new BadInputException("Low price cannot be negative.");
            scanner.nextLine();

            System.out.print("\nEnter high price amount (float): ");
            float highValue = scanner.nextFloat();
            if (highValue < 0.0f)
                throw new BadInputException("High price cannot be negative.");
            if (highValue <= lowValue)
                throw new BadInputException("High price must be higher than the"
                    + "low price amount");
            scanner.nextLine();
            
            System.out.println("---------------------------------------------------"
                + "-------------------------------------------------------");
            System.out.format(
                "| %12s | %12s | %8s | %8s | %6s | %9s | %10s | %17s | %n", 
                "VEHICLE TYPE", "VIN", "MAKE", "MODEL", "YEAR", "MILEAGE", 
                "PRICE", "EXTRA DETAILS");
            System.out.println("---------------------------------------------------"
                    + "-------------------------------------------------------"); 
        }
	}

	/**
	 * Allows the customer to send a message/question to the dealership. The 
     * method only handles the user input/output. To perform the necessary 
     * operations for the message handling, it calls the appropriate methods 
     * of the Controller classes of the program.
	 */
	private void sendMsg() throws InterruptedException {
        String message = null;
        
        scanner.nextLine();
        
		System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        
        if (!dealership.getUserRecords().getUsers().containsValue(email)) {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
              
            System.out.print("Enter your phone number (555-555-5555): ");
            String phone = scanner.nextLine();
              
            dealership.getUserRecords().addCustomer(name, phone, email, 00000);
        }
        
        for (User u : dealership.getUserRecords().getUsers().values()) {
          
          if (u instanceof Customer && email.equals(((Customer) u).getEmail())) {
              System.out.print("Enter your message: ");
              
              message = scanner.nextLine();
                
              dealership.getCommMgr().newMessage(((Customer) u), message);
          } 
        }  
    }
}