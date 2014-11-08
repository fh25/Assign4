package assign4;

/**
 * Customer class that extends User.
 * @author fhj
 */
public class Customer extends User {

    /**
     * private variable int to store a Customer's Driver's License Number.
     */
	private int driverLicNo;
    
    /**
     * private String phoneNo to store the Customer's Phone Number.
     */
	private String phoneNo;
    
    /**
     * private String email to store the Customer's email address.
     */
	private String email;

    /**
     * Returns the driver's license number.
     * @return 
     */
    public int getDriverLicNo() {
      return driverLicNo;
    }

    /**
     * Sets the Driver's License Number.
     * @param driverLicNo 
     */
    public void setDriverLicNo(int driverLicNo) {
      this.driverLicNo = driverLicNo;
    }

    /**
     * Returns the phone number.
     * @return 
     */
    public String getPhoneNo() {
      return phoneNo;
    }

    /**
     * Sets the phone number.
     * @param phoneNo 
     */
    public void setPhoneNo(String phoneNo) {
      this.phoneNo = phoneNo;
    }

    /**
     * Returns the email address.
     * @return 
     */
    public String getEmail() {
      return email;
    }
    
    /**
     * Sets the email address.
     * @param email 
     */
    public void setEmail(String email) {
      this.email = email;
    }
    
    /**
     * Print the attributes of the customer, in a formatted fashion.
     */
    public void print() {
        System.out.format("| %10s | %9d | %12s | DriverLic#: %12s | Ph#: %12s | Email: %12s | %n", 
                "Customer", id, name, driverLicNo, phoneNo, email );
    }

    /**
     * Overrides toString
     * @return 
     */
    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", Name=" + name 
                + ", driverLic#=" + driverLicNo + ", Phone#=" + phoneNo 
                + ", Email=" + email + '}';
    }

}