package assign4;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

/**
 * UserRecords class that implements Serializable.
 * @author fhj
 */
public class UserRecords implements Serializable {

	/**
	 * A collection of the users in our system. We anticipate many user look 
     * up operations during system use, hence, we opted for the use of a 
     * <UserID, User> map collection type, for faster look up. Using a map, 
     * allows us to find a user using their id in constant time.
	 */
	private Map<Integer, User> users;
    
    /**
     * private static int userCount.
     */
    private static int userCount = 0;

    /**
     * Default constructor.
     */
    public UserRecords() {
      users = new Hashtable<>();
    }
    
    /**
     * Map data structure for storing users.
     * @return 
     */
    public Map<Integer, User> getUsers() {
      return users;
    }
    
	/**
	 * 
	 * @param name
	 * @param phoneNo
	 * @param email
	 * @param driverLicNo
	 */
	public void addCustomer(String name, String phoneNo, String email, int driverLicNo) {
        Customer customer = new Customer();
        
        customer.setName(name);
        customer.setPhoneNo(phoneNo);
        customer.setEmail(email);
        customer.setDriverLicNo(driverLicNo);
        customer.setId(++userCount);  
        
        users.put(customer.getId(), customer);
	}

	/**
	 * 
	 * @param name
	 * @param monthlySal
	 * @param bankAccNo
     * @param status
	 * @param pin
	 */
	public void addEmployee(String name, float monthlySal, int bankAccNo, 
        boolean status, int pin) {
		Employee emp = new Employee();
        
        emp.setName(name);
        emp.setMonthlySal(monthlySal);
        emp.setBankAccNo(bankAccNo);
        emp.setActive(status);
        emp.setPin(pin);
        emp.setId(++userCount);
        
        users.put(emp.getId(), emp);
	}

	/**
	 * 
	 * @param name
	 * @param monthlySal
	 * @param bankAccNo
     * @param status
	 * @param pin
	 */
	public void addAdmin(String name, float monthlySal, int bankAccNo, 
        boolean status, int pin) {
		Administrator admin = new Administrator();
        
        admin.setName(name);
        admin.setMonthlySal(monthlySal);
        admin.setBankAccNo(bankAccNo);
        admin.setActive(status);
        admin.setPin(pin);
        admin.setId(++userCount);
        
        users.put(admin.getId(), admin);
	}

	/**
	 * 
	 * @param name
	 * @param phoneNo
	 */
	public void addSupplier(String name, String phoneNo) {
		Supplier sup = new Supplier();
        
        sup.setName(name);
        sup.setPhoneNo(phoneNo);
        sup.setId(++userCount);
        
        users.put(sup.getId(), sup);
	}
    
    /**
     * This method updates an Employee and throws a BadInputException if the 
     * input isn't what is expected. 
     * @param id
     * @throws BadInputException 
     */
    public void updateEmployee (int id) throws BadInputException {
        int option = 0;
        Scanner scan = new Scanner(System.in);
        
        if (users.containsKey(id)) {
            System.out.println("Select update option:\n"
                + "1. Monthly Salary\n"
                + "2. Bank Account No.\n"
                + "3. Status (Active or Inactive)\n"
                + "4. PIN\n"
                + "5. Exit\n");
            System.out.print("Your choice: ");

            option = scan.nextInt();
            if (option < 1 || option > 5) {
                throw new BadInputException("Legal vehicle type values: 1-5.");
            } else if (option ==5) {
                return;
            }
        } else {
          System.out.println("Couldn't find Employee ID: " + id );
          return;
        }
 
        for (User user : users.values()) {
          if (user instanceof Employee) {
            if (id == ((Employee) user).getId()) {
                if (option == 1) {
                    System.out.print("Enter " + user.getName() + "'s new "
                        + "Monthly Salary: ");
                    float newSalary = scan.nextFloat();
                    ((Employee) user).setMonthlySal(newSalary);
                } else if (option == 2) {
                    System.out.print("Enter " + user.getName() + "'s new "
                        + "Bank Account Number (int): ");
                    int newBankAcctNo = scan.nextInt();
                    ((Employee) user).setBankAccNo(newBankAcctNo);
                } else if (option == 3) {
                    System.out.print("Change " + user.getName() + "'s status "
                        + "(true or false): ");
                    boolean status = scan.nextBoolean();
                    ((Employee) user).setActive(status);
                } else if (option == 4) {
                    System.out.print("Change " + user.getName() + "'s PIN (int): ");
                    int newPin = scan.nextInt();
                    ((Employee) user).setPin(newPin);
                }
            } 
          }
        }
    }
    
    /**
     * This method updates a Customer and throws a BadInputException if the 
     * input isn't what is expected. 
     * @param id
     * @throws BadInputException 
     */
    public void updateCustomer (int id) throws BadInputException {
        int option = 0;
        Scanner scan = new Scanner(System.in);
        
        if (users.containsKey(id)) {
            System.out.println("Select update option:\n"
                + "1. Driver's License Number.\n"
                + "2. Phone Number.\n"
                + "3. Email Address.\n"
                + "4. Exit\n");
            System.out.print("Your choice: ");

            option = scan.nextInt();
            if (option < 1 || option > 4) {
                throw new BadInputException("Legal vehicle type values: 1-4.");
            } else if (option ==4) {
                return;
            }
        } else {
          System.out.println("Couldn't find Customer ID: " + id );
          return;
        }
        scan.nextLine();
 
        for (User user : users.values()) {
          if (user instanceof Customer) {
            if (id == ((Customer) user).getId()) {
                if (option == 1) {
                    System.out.print("Enter " + user.getName() + "'s new "
                        + "Driver's License No.: ");
                    int driverLicense = scan.nextInt();
                    if ( users.containsValue(driverLicense) ) {
                       System.out.print("Driver License exists! Can't update");
                       return;
                    }
                    ((Customer) user).setDriverLicNo(driverLicense);
                } else if (option == 2) {
                    System.out.print("Enter " + user.getName() + "'s new "
                        + "phone number: ");
                    String phoneNo = scan.nextLine();
                    ((Customer) user).setPhoneNo(phoneNo);
                } else if (option == 3) {
                    System.out.print("Change " + user.getName() + "'s email: ");
                    String email = scan.nextLine();
                    if ( users.containsValue(email) ) {
                       System.out.print("Email address exists! Can't update");
                       return;
                    }
                    ((Customer) user).setEmail(email);
                }
            } 
          }
        }
    }
    
    /**
     * This method deletes a Customer based on their User id.
     * @param id 
     */
    public void deleteCustomer (int id) {
        if (users.get(id) instanceof Customer && users.containsKey(id)) {
          users.remove(id);
        } else {
            System.out.println("\nID is not a Customer.");
          }
    }
    
    /**
     * This method validates a Supplier based on their User id.
     * @param id
     * @return 
     */
    public Boolean validateSupplier (int id) {
      for (User user : users.values()) {
          if (user instanceof Supplier && users.containsKey(id)) {
              return true;
          } 
      }
      return false;
    }
    
    /**
     * This method validates a Customer based on their User id.
     * @param id
     * @return 
     */
    public Boolean validateCustomer (int id) {
      for (User user : users.values()) {
          if (user instanceof Customer && users.containsKey(id)) {
              return true;
          } 
      }
      return false;
    }
    
    /**
     * This method validates an Employee or Administrator based on their User id.
     * @param id
     * @return 
     */
    public Boolean validateEmployee (int id) {
      for (User user : users.values()) {
          if (user instanceof Employee && users.containsKey(id)) {
              return true;
          } 
      }
      return false;
    }
    
    /**
     * This method prints all users.
     */
    public void printUsers() {
        System.out.println("\n" + users);
    }
}