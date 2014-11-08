package assign4;

/**
 * Supplier class that extends User.
 * @author fhj
 */
public class Supplier extends User {

    /**
     * private String phoneNo.
     */
	private String phoneNo;

    /**
     * Returns phoneNo.
     * @return 
     */
    public String getPhoneNo() {
      return phoneNo;
    }

    /**
     * Sets the phoneNo.
     * @param phoneNo 
     */
    public void setPhoneNo(String phoneNo) {
      this.phoneNo = phoneNo;
    }
    
    /**
     * Overrides toString.
     * @return 
     */
    @Override
	public String toString() 
	{
		return "Supplier{" + "id=" + id + ", Name=" + name 
				+ ", Phone Number =" + phoneNo + '}';
	}
}