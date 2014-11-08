package assign4;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GeneralView class that extends ConsoleUserInterface and implements Serializable
 * @author fhj
 */
public class GeneralView extends ConsoleUserInterface implements Serializable {

	/**
	 * 
	 * @param dealership
	 */
	public GeneralView(Dealership dealership) {
		// TODO - implement GeneralView.GeneralView
        GeneralView.dealership = dealership;
	}
    
    /**
     * Overrides the printMenu.
     */
    @Override
	protected void printMenu() {
		// TODO - implement GeneralView.printMenu
        System.out.println(
          "\nGeneral View\nChoose one of the following.\n"      
          + "1. Switch to Customer View.\n "
          + "2. Switch to Employee View.\n"
          + "3. Switch to Admin View.\n"
          + "4. Exit program.");
	}
    
    /**
     * Overrides the run.
     */
    @Override
	public void run() {
	    // TODO - implement GeneralView.run
        int choice = 0;
        Boolean exit = false;
        do {
          printMenu();
          try {
              System.out.print("Enter choice: ");
              choice = scanner.nextInt();
              
              switch (choice) {
                  case 1: switchToCustView(); break;
                  case 2: switchToEmpView(); break;
                  case 3: switchToAdminView(); break;
                  case 4: exit = true; System.out.print("System exited."); return;
                  default: System.err.println("Please select a number between"
                      + "1 and 4.");
              }
          } catch (InputMismatchException ex) {
                System.err.println("Input not valid. Please Try again.");
                scanner.nextLine();
                continue;
          } catch (BadInputException ex) {
            Logger.getLogger(GeneralView.class.getName()).log(Level.SEVERE, null, ex);
          }
        } while (!exit);
	}

    /**
	 * Switches console interface to customer view.
	 */
	private void switchToCustView() {
        new CustomerView().run();
	}

	/**
	 * Switches console interface to employee view. To complete the operation 
     * it requests the employee to enter their PIN number.
	 */
	private void switchToEmpView() throws BadInputException {
		// TODO - implement GeneralView.switchToEmpView
        System.out.print("\nEnter Employee ID: ");
        int id = scanner.nextInt();
        if (id < 0) 
          throw new BadInputException("ID can't be negative.");
            
        System.out.print("Enter Employee PIN: ");
        int pin = scanner.nextInt();
        if (pin < 0) 
          throw new BadInputException("PIN can't be negative.");
        
        for (User user : dealership.getUserRecords().getUsers().values()) {
          if (user instanceof Employee || user instanceof Administrator) {
            if (id == ((Employee) user).getId() && pin == ((Employee) user).getPin()) {
                new EmployeeView().run();
            } else {
                System.out.println("Employee either doesn't exist or entered an "
                + "invalid PIN.");
                return;
            }
          }
        }
	}

	/**
	 * Switches console interface to admin view. To complete the operation it 
     * requests the admin to enter their PIN number. The first admin user of the 
     * system has a standard PIN that is known by the system and the user. For 
     * every other admin or employee added to the system, a new PIN should be 
     * entered.
	 */
	private void switchToAdminView() throws BadInputException {
		// TODO - implement GeneralView.switchToAdminView
        System.out.print("\nEnter Admin ID: ");
        int id = scanner.nextInt();
        if (id < 0) 
          throw new BadInputException("ID can't be negative.");
            
        System.out.print("Enter Admin PIN: ");
        int pin = scanner.nextInt();
        if (pin < 0) 
          throw new BadInputException("PIN can't be negative.");
        
        for (User user : dealership.getUserRecords().getUsers().values()) {
          if (user instanceof Administrator) {
            if (id == ((Administrator) user).getId() 
                && pin == ((Administrator) user).getPin()) {
                new AdminView().run();
            } else {
                System.out.println("Admin either doesn't exist or entered an "
                + "invalid PIN.");
                return;
            }
          }
          
        }
	}
}