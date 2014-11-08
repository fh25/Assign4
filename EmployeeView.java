package assign4;

import static assign4.ConsoleUserInterface.scanner;
import java.io.IOException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EmployeeView class that extends ConsoleUserInterface.
 * @author fhj
 */
public class EmployeeView extends ConsoleUserInterface {
    
    /**
     * private static final logged that logs messages to the console and to a 
     * file.
     */
    private static final Logger logged = Logger.getLogger(EmployeeView.class.getName());
    
    /**
     * Overrides printMenu
     */
    @Override
	protected void printMenu() {
		// TODO - implement EmployeeView.printMenu
        System.out.println(
          "\nEmployee View\nChoose one of the following.\n"      
          + " 1. Sell (Remove) vehicle.\n "
          + " 2. Purchase(Add) vehicle.\n"
          + " 3. Add a Customer.\n"
          + " 4. Add a Supplier.\n"
          + " 5. Update Customer.\n"
          + " 6. Update a Vehicle.\n"
          + " 7. Delete Customer.\n"
          + " 8. Search Inventory.\n"
          + " 9. Respond to Message.\n"
          + "10. Print All Users.\n"
          + "11. Print All Vehicles.\n"
          + "12. Exit");
	}
    
    /**
     * Overrides run method.
     */
    @Override
	public void run() {
		// TODO - implement EmployeeView.run
        int choice = 0;
        Boolean exit = false;
        do {
          printMenu();
          try {
              System.out.print("Enter choice: ");
              choice = scanner.nextInt();
              
              switch (choice) {
                  case 1: sellVehicle(); break;
                  case 2: purchaseVehicle(); break;
                  case 3: addCustomer(); break;
                  case 4: addSupplier(); break;
                  case 5: updateCustomer(); break;
                  case 6: updateVehicle(); break;
                  case 7: deleteCustomer(); break;
                  case 8: searchInventory(); break;
                  case 9: respondToMsg(); break;
                  case 10: dealership.getUserRecords().printUsers(); break;
                  case 11: dealership.getVehInv().printVehicles(); break;
                  case 12: exit = true; break;
                  default: System.err.println("Please select a number between"
                      + "1 and 12.");
              }
          } catch (InputMismatchException ex) {
                System.err.println("Input not valid. Please Try again.");
                scanner.nextLine();
                continue;
          } catch (BadInputException ex) {
            Logger.getLogger(EmployeeView.class.getName()).log(Level.WARNING, null, ex);
            try {
              logged.addHandler(new FileHandler("logs.%u.%g.txt"));
            } catch (IOException ex1) {
              Logger.getLogger(EmployeeView.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
              Logger.getLogger(EmployeeView.class.getName()).log(Level.SEVERE, null, ex1);
            }
            scanner.nextLine();
          } catch (InterruptedException ex) {
            Logger.getLogger(EmployeeView.class.getName()).log(Level.SEVERE, null, ex);
          }
        } while (!exit);
		
	}

    /**
     * This method purchaseVehicle collects vehicle, customer and employee information
     * and then calls a method in Transactions.
     * @throws BadInputException 
     */
    public void purchaseVehicle() throws BadInputException {
        
        System.out.print("\nEnter the Supplier ID (int): ");
        int supplierId = scanner.nextInt();
        if (supplierId < 1)
            throw new BadInputException("Supplier ID can't be negative.");
        scanner.nextLine();
        
        if (!dealership.getUserRecords().validateSupplier(supplierId)) {
          System.out.print("\nSupplier ID doesn't exist. Enter the following"
              + " information to add the Supplier: ");
          addSupplier();
        }
        
        System.out.print("\nEnter Vehicle Price (float): ");
        float price = scanner.nextFloat();
        if (price < 0.0f)
            throw new BadInputException("Price cannot be negative.");
        scanner.nextLine();
        
        System.out.println("\nSelect vehicle type:\n"
                + "1. Passenger car\n"
                + "2. Truck\n"
                + "3. Motorcycle");
        System.out.print("Enter Choice: ");
        int vehicleType = scanner.nextInt();
        if (vehicleType < 1 || vehicleType > 3)
            throw new BadInputException("Legal vehicle type values: 1-3.");
        scanner.nextLine();
        
        System.out.print("\nEnter VIN (string less than 10 chars): ");
        String vin = scanner.nextLine();
        if (vin.length() > 10)
            throw new BadInputException("VIN should not be more that 10 "
                + "characters long.");
        
        Date currentDate = new Date(System.currentTimeMillis());
        
        System.out.print("\nEnter Make (string): ");
        String make = scanner.nextLine();
        
        System.out.print("\nEnter Model (string): ");
        String model = scanner.nextLine();
        
        System.out.print("\nEnter Year (int): ");
        int year = scanner.nextInt();
        
        System.out.print("\nEnter Mileage (int): ");
        int mileage = scanner.nextInt();
        if (mileage < 0)
            throw new BadInputException("Mileage cannot be negative.");
        
        if (vehicleType == 1) {
            scanner.nextLine();
            System.out.print("\nEnter body style (string): ");
            String bodyStyle = scanner.nextLine();
        
            dealership.getVehInv().addPassengerCar(vin, make, model, year, 
                mileage, price, bodyStyle);
            
        } else if (vehicleType == 2) {
            System.out.print("\nEnter max load weight (lb), (float): ");
            float maxLoad = scanner.nextFloat();
            if (maxLoad < 0.0f)
                throw new BadInputException("Max load cannot be negative.");
            
            System.out.print("\nEnter truck length (ft), (float): ");
            float tLength = scanner.nextFloat();
            if (tLength < 0.0f)
                throw new BadInputException("Truck length cannot be negative.");
            
            dealership.getVehInv().addTruck(vin, make, model, year, mileage, 
                price, maxLoad, tLength);
        
        } else if (vehicleType == 3) {
            scanner.nextLine();
            System.out.print("\nEnter motorcycle type (string): ");
            String type = scanner.nextLine();
            
            System.out.print("\nEnter engine displacement: ");
            int displacement = scanner.nextInt();
            if (displacement < 0.0f)
                throw new BadInputException("Displacement cannot be negative.");
            
            dealership.getVehInv().addMotorcycle(vin, make, model, year, 
                mileage, price, type, mileage);
           
        } else {
            System.out.println("Unknown vehicle type. Please try again.");
        }
        
        dealership.getTransRec().completePurchTrans(vin, currentDate, year, 
            supplierId, price);
    }
    
    /**
     * This method sellVehicle collects vehicle, customer and employee information
     * and then calls a method in Transactions.
     * @throws BadInputException 
     */
	public void sellVehicle() throws BadInputException {
        
        System.out.print("\nEnter customer ID (int): ");
        int customerId = scanner.nextInt();
        
        scanner.nextLine();
        if (!dealership.getUserRecords().validateCustomer(customerId)) {
          System.out.println("\nCustomer ID doesn't exist");
          addCustomer();
        }
               
        System.out.print("\nEnter employee ID (int): ");
        int employeeId = scanner.nextInt();
        
        scanner.nextLine();
        if (!dealership.getUserRecords().validateEmployee(employeeId)) {
          System.out.println("\nEmployee ID doesn't exist");
          return;
        }
        
        System.out.print("\nEnter VIN (string): ");
        String vin = scanner.nextLine();
        if (vin.length() > 10)
            throw new BadInputException("VIN should not be more that 10 "
                + "characters long.");
        
        for (Vehicle v : dealership.getVehInv().getVehicles()) {
          
          if (v.getVin().equals(vin)) { 
              break;
          } else if (v.getVin() == null) {
              System.out.print("\nThe vehicle with the VIN you are trying to "
              + "sell does not exist in the database. Aborting transaction.");
              return;
            }
        }
        
        Date currentDate = new Date(System.currentTimeMillis());
        
        System.out.print("\nEnter sale price (float): ");
        float price = scanner.nextFloat();
        if (price < 0.0f)
            throw new BadInputException("Sale price cannot be negative.");
          
        dealership.getTransRec().completeSaleTrans(vin, currentDate, employeeId, 
            customerId, price);
        
        System.out.println("\nTransaction Completed!");
	}

    /**
     * This method addCustomer prompts for Customer information which is sent
     * to the UserRecords to be added.
     */
	public void addCustomer() {

		String name = "";
		int driverLicense = 0;
		String phone = "";
		String email = "";
		
	    try {
          System.out.print("Enter Customer Name: ");
          name = scanner.next();        
        } catch (InputMismatchException ex) {
          System.err.println("Input mismatch. Please try again.");
          scanner.nextLine();
        }
		
	    try {
          System.out.print("Enter " + name + "'s Drivers License#: ");
          driverLicense = scanner.nextInt();
        } catch (InputMismatchException ex) {
          System.err.println("Input mismatch. Please try again.");
          scanner.nextLine();
        }

        try {
          System.out.print("Enter " + name + "'s Phone#: ");
          phone = scanner.next();
        } catch (InputMismatchException ex) {
          System.err.println("Input mismatch. Please try again.");
          scanner.nextLine();
        }

        try {
          System.out.print("Enter " + name + "'s Email: ");
          email = scanner.next();
        } catch (InputMismatchException ex) {
          System.err.println("Input mismatch. Please try again.");
          scanner.nextLine();
        }
		
		dealership.getUserRecords().addCustomer(name, phone, email, driverLicense);    	
	}
    
    /**
     * This method addSupplier prompts for the supplier name and phone number
     * and calls a method in UserRecords to add a Supplier.
     */
    public void addSupplier() {
        
        System.out.print("\nEnter Supplier name: ");
        String supplier = scanner.nextLine();
        
        System.out.print("\nEnter Supplier phone number: ");
        String phone = scanner.nextLine();
        
        dealership.getUserRecords().addSupplier(supplier, phone);
        
        System.out.print("\nSupplier Added.\n");
    }

    /**
     * This method updateCustomer prompts for the customer ID. If not found
     * the method will return. If found a call is made to UserRecords to 
     * update a Customer.
     * @throws BadInputException 
     */
	public void updateCustomer() throws BadInputException {
      
        System.out.print("Enter the Customer ID you wish to update: ");
        int id = scanner.nextInt();
        
        if (!dealership.getUserRecords().validateCustomer(id)) {
          System.out.println("\nEmployee ID doesn't exist");
          return;
        }
        
        if (id < 0) {
          throw new BadInputException("ID can't be negative.");
        }
        scanner.nextLine();
        dealership.getUserRecords().updateCustomer(id);
	}
    
    /**
     * This method updateVehicle prompts for a VIN, if not found the method 
     * returns. If found a call is made to VehicleInventory to update a vehicle.
     * @throws BadInputException 
     */
    public void updateVehicle() throws BadInputException {
        scanner.nextLine();
        System.out.print("Enter VIN: ");
        String vin = scanner.nextLine();
        
        if (!dealership.getVehInv().verifyVehicle(vin)) {
          System.out.print("\nVehicle VIN not found!\n");
          return;
        } else {
          System.out.print("\nVehicle VIN found!\n");
        }
        
        dealership.getVehInv().updateVehicle(vin);
    }

    /**
     * This method deleteCustomer prompts for the Customer ID. A call is made
     * to UserRecords to delete a Customer.
     * @throws BadInputException 
     */
	public void deleteCustomer() throws BadInputException {
		System.out.print("Enter the Customer ID you wish to DELETE: ");
        int id = scanner.nextInt();
        if (id < 0) {
          throw new BadInputException("ID can't be negative.");
        }
        
        dealership.getUserRecords().deleteCustomer(id);
	}

    /**
     * This method searchInventory prompts for the vin then calls a method
     * in VehicleInventory to search the inventory.
     * @throws InterruptedException 
     */
	public void searchInventory() throws InterruptedException {
	    System.out.print("Enter Vehicle Vin: ");
		String vin = scanner.nextLine();
        
        BusyThread busy = new BusyThread();
		busy.start();
		
        dealership.getVehInv().searchInventory(vin);

		System.out.println("\nVehicle found: ");	
	}

    /**
     * This method respondToMsg allows an Employee to respond to a Customer
     * message.
     * @throws BadInputException 
     */
	public void respondToMsg() throws BadInputException {
      
      System.out.print("\nEnter Employee ID: ");
      int id = scanner.nextInt();
        
      int choice = 0;
      Boolean exit = false;
        do {
          System.out.println("\nSelect an option:\n"
              + "1. Check for Messages.\n"
              + "2. Respond to Message.\n"
              + "3. Print Answered Messages.\n"
              + "4. Exit");
 
          try {
              System.out.print("Enter choice: ");
              choice = scanner.nextInt();
              
              scanner.nextLine();
              switch (choice) {
                  case 1: System.out.println(dealership.getCommMgr().getNewMessages().peek()); 
                          break;
                  case 2: System.out.println(dealership.getCommMgr().getNewMessages().peek()); 
                                            
                          System.out.println("Enter response: ");
                          String response = scanner.nextLine();                           
                                   
                          for (User u : dealership.getUserRecords().getUsers().values()) {
                            if (u instanceof Employee) {
                              if (id == u.getId()) {
                                dealership.getCommMgr().respondToMessage(dealership.getCommMgr().getNewMessages().poll(), 
                                (Employee) u, response);
                                break;
                              }
                            }
                          }                          
                  case 3: dealership.getCommMgr().printAnsweredMessages(); break;
                  case 4: exit = true; break;
                  default: System.err.println("Please select a number between"
                      + "1 and 4.");
              }
          } catch (InputMismatchException ex) {
                System.err.println("Input not valid. Please Try again.");
                scanner.nextLine();
                continue;
          }
        } while (!exit);
	}

}