package assign4;

/**
 * Employee class that extends User.
 * @author fhj
 */
public class Employee extends User {

    /**
     * protected float monthlySal which stores the salary for an employee.
     */
	protected float monthlySal;
    
    /**
     * protected int bankAccNo which stores the bank number for an employee.
     */
	protected int bankAccNo;
    
    /**
     * protected boolean active which stores the status of an Employee.
     */
	protected boolean active;
    
	/**
	 * A 4 digit PIN number that identifies the employee.
	 */
	protected int pin;
    
    /**
     * Default Constructor.
     */
    public Employee () {
      this.monthlySal = 0.0f;
      this.bankAccNo = 0;
      this.active = false;
      this.pin = 0;
    }

    /**
     * Returns monthlSal.
     * @return 
     */
    public float getMonthlySal() {
      return monthlySal;
    }

    /**
     * Sets the monthly salary.
     * @param monthlySal 
     */
    public void setMonthlySal(float monthlySal) {
      this.monthlySal = monthlySal;
    }

    /**
     * Returns bankAccNo.
     * @return 
     */
    public int getBankAccNo() {
      return bankAccNo;
    }

    /**
     * Sets the bank account number.
     * @param bankAccNo 
     */
    public void setBankAccNo(int bankAccNo) {
      this.bankAccNo = bankAccNo;
    }

    /**
     * Returns active.
     * @return 
     */
    public boolean isActive() {
      return active;
    }

    /**
     * Sets whether an Employee is active or not.
     * @param active 
     */
    public void setActive(boolean active) {
      this.active = active;
    }

    /**
     * Returns pin.
     * @return 
     */
    public int getPin() {
      return pin;
    }

    /**
     * Sets pin of an Employee.
     * @param pin 
     */
    public void setPin(int pin) {
      this.pin = pin;
    }
    
    /**
	 * Print the attributes of the employee, in a formatted fashion.
	 */
	public void print() 
	{
		System.out.format("| %10s | %9d | %12s | Salary: %9s, Bank#: %8d | Active: %9s | Pin: %9d | %n", 
				"Employee", id, name, monthlySal, bankAccNo, active, pin);
	}

    /**
     * Overrides toString.
     * @return 
     */
	@Override
	public String toString() 
	{
		return "Employee{" + "id=" + id + ", Name=" + name 
				+ ", monthlySalary=" + monthlySal 
				+ ", bankAccountNumber=" + bankAccNo
				+ ", active=" + active + ", pin=" + pin + '}';
	}
}