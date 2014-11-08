package assign4;

/**
 * Sale class that extends Transaction
 * @author fhj
 */
public class Sale extends Transaction {

    /**
     * private int custId that stores the customer id.
     */
	private int custId;
    
    /**
     * private float salePrice that stores the vehicle sales price.
     */
	private float salePrice;

    /**
     * Sets the custId.
     * @param custId 
     */
    public void setCustId(int custId) {
      this.custId = custId;
    }

    /**
     * Sets the salePrice.
     * @param salePrice 
     */
    public void setSalePrice(float salePrice) {
      this.salePrice = salePrice;
    }   
}