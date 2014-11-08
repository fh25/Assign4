package assign4;

import java.io.Serializable;
import java.util.Date;

/**
 * Transaction abstract class that implements Serializable. 
 * @author fhj
 */
public abstract class Transaction implements Serializable {

    /**
     * protected String vin.
     */
	protected String vin;
    
    /**
     * protected Date date.
     */
	protected Date date;
    
    /**
     * protected int empId.
     */
	protected int empId;
    
    /**
     * protected int invoiceNo.
     */
	protected int invoiceNo;

    /**
     * Returns vin.
     * @return 
     */
    public String getVin() {
      return vin;
    }

    /**
     * Returns date.
     * @return 
     */
    public Date getDate() {
      return date;
    }

    /**
     * Returns empId.
     * @return 
     */
    public int getEmpId() {
      return empId;
    }

    /**
     * Returns invoiceNo.
     * @return 
     */
    public int getInvoiceNo() {
      return invoiceNo;
    }

    /**
     * Sets the vin.
     * @param vin 
     */
    public void setVin(String vin) {
      this.vin = vin;
    }
    
    /**
     * Sets the date.
     * @param date 
     */
    public void setDate(Date date) {
      this.date = date;
    }

    /**
     * Sets the empId.
     * @param empId 
     */
    public void setEmpId(int empId) {
      this.empId = empId;
    }
    
    /**
     * Sets the invoiceNo.
     * @param invoiceNo 
     */
    public void setInvoiceNo(int invoiceNo) {
      this.invoiceNo = invoiceNo;
    }

}