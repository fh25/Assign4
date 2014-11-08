package assign4;

/**
 * Purchase class that extends Transaction
 * @author fhj
 */
public class Purchase extends Transaction {

    /**
     * private int supplierID which stores the supplier id.
     */
	private int supplierID;
    
    /**
     * private float purchPrice which stores the vehicle purchase price.
     */
	private float purchPrice;

    /**
     * Sets the supplierID.
     * @param supplierID 
     */
    public void setSupplierID(int supplierID) {
      this.supplierID = supplierID;
    }

    /**
     * Sets the purchPrice.
     * @param purchPrice 
     */
    public void setPurchPrice(float purchPrice) {
      this.purchPrice = purchPrice;
    }

}