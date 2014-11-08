package assign4;

import static assign4.ConsoleUserInterface.scanner;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AdminView that extends ConsoleUserInterface
 * @author fhj
 */
public class AdminView extends ConsoleUserInterface {
  
    /**
     * Logger variable for logging errors both to the console and to a file.
     */
    private static final Logger logged = Logger.getLogger(AdminView.class.getName());
    
    /**
     * Default Constructor
     */
    public AdminView() {
      
    }
    
    /**
     * Override printMenu of ConsoleUserInterface
     */
    @Override
	protected void printMenu() {
		// TODO - implement AdminView.printMenu
        System.out.println(
          "\nAdmin View\nChoose one of the following.\n"      
          + "1. Add Employee.\n "
          + "2. Update Employee.\n"
          + "3. Print All Users.\n"
          + "4. Exit program.");
		//throw new UnsupportedOperationException();
	}

    /**
     * Override run of ConsoleUserInterface
     */
    @Override
	public void run() {
		// TODO - implement AdminView.run
        int choice = 0;
        Boolean exit = false;
        do {
          printMenu();
          try {
              System.out.print("Enter choice: ");
              choice = scanner.nextInt();
              
              switch (choice) {
                  case 1: addEmployee(); break;
                  case 2: updateEmployee(); break;
                  case 3: dealership.getUserRecords().printUsers(); break;
                  case 4: exit = true; break;
                  default: System.err.println("Please select a number between"
                      + "1 and 4.");
              }
          } catch (InputMismatchException ex) {
                System.err.println("Input not valid. Please Try again.");
                Logger.getLogger(CustomerView.class.getName()).log(Level.WARNING, 
                    "Input is not of type int!");
            try {
              logged.addHandler(new FileHandler("logs.%u.%g.txt"));
            } catch (IOException ex1) {
              Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
              Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex1);
            }
                scanner.nextLine();
                continue;
          } catch (BadInputException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
          }
        } while (!exit);
	}

    /**
     * This method adds an Employee.
     */
	public void addEmployee() {
      
        String name = "";
        float salary = 0f;
        int bankAcctNo = 0;
        int pin = 0;
        
        System.out.print("Enter Employee name: ");
        name = scanner.next();
        
        
        try {
          System.out.print("Enter " + name + "'s Salary: ");
          salary = scanner.nextFloat();
          if (salary <= 0)
            throw new BadInputException("Salary can't be less than or equal to 0.");
        } catch (BadInputException ex) {
          Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
          System.out.print("Enter " + name + "'s Bank Account No.: ");
          bankAcctNo = scanner.nextInt();
          if (bankAcctNo < 0)
              throw new BadInputException("Bank Account No. must be positive.");
        } catch (BadInputException ex) {
          Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        try {
          System.out.print("Enter " + name + "'s PIN: ");
          pin = scanner.nextInt();
          if (pin <= 0 )
            throw new BadInputException("PIN can't be less than 0.");
        } catch (BadInputException ex) {
          Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        dealership.getUserRecords().addEmployee(name, salary, bankAcctNo, true, pin);
	}

    /**
     * This method updates an Employee's information.
     * @throws BadInputException 
     */
	public void updateEmployee() throws BadInputException {
      System.out.println("\nEnter Employee ID: ");
      int id = scanner.nextInt();
      
      if (!dealership.getUserRecords().validateEmployee(id)) {
          System.out.println("\nEmployee ID doesn't exist");
          return;
        }
      
      dealership.getUserRecords().updateEmployee(id);
      	
	}
}