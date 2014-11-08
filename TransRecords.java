package assign4;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 * TransRecords class that implements Serializable. This class stores all 
 * vehicle transactions.
 * @author fhj
 */
public class TransRecords implements Serializable {

	/**
	 * A set of all the transactions completed at the dealership. We don't 
     * want to allow duplicate transactions but care about the order in which 
     * the transactions were completed, hence, we opted in using a LinkedHashSet 
     * collection type.
	 */
	private LinkedHashSet<Transaction> transactions;
    
	/**
	 * A reference to a Dealership object. This class field is used for 
     * convenience purposes, so we can access the the inventory through the 
     * dealership object. The inventory needs to be updated after every 
     * completed transaction.
	 */
	private Dealership dealership;
    
    /**
     * private static in invoiceCount.
     */
    private static int invoiceCount = 0;
    
    /**
     * Default Constructor.
     * @param dealership 
     */
    TransRecords (Dealership dealership) {
      this.dealership = dealership;
      this.transactions = new LinkedHashSet<>();
    }
    
    /**
     * Returns transactions LinkedHashSet
     * @return 
     */
    public LinkedHashSet<Transaction> getTransactions() {
      return transactions;
    } 
      
	/**
	 * Completes a sale transaction and adds a new sale transaction object to 
     * the set of completed transactions.
	 * @param vin
	 * @param date
	 * @param empId
	 * @param custId
	 * @param salePrice
	 */
	public void completeSaleTrans(String vin, Date date, int empId, int custId, 
        float salePrice) {
        Sale sale = new Sale();
        
        sale.setVin(vin);
        sale.setDate(date);
        sale.setEmpId(empId);
        sale.setCustId(custId);
        sale.setSalePrice(salePrice);
        sale.setInvoiceNo(++invoiceCount);
        transactions.add(sale);
        
        updateInventory(vin);
	}

	/**
	 * Completes a purchase transaction and adds a new purchase transaction  
     * object to the set of completed transactions.
	 * @param vin
	 * @param date
	 * @param empId
	 * @param supplierId
	 * @param purchasePrice
	 */
	public void completePurchTrans(String vin, Date date, int empId, 
        int supplierId, float purchasePrice) {
		// TODO - implement TransRecords.completePurchTrans
        Purchase purch = new Purchase();
        
        purch.setVin(vin);
        purch.setDate(date);
        purch.setEmpId(empId);
        purch.setSupplierID(supplierId);
        purch.setPurchPrice(purchasePrice);
        purch.setInvoiceNo(++invoiceCount);
        transactions.add(purch);
	}

	/**
	 * This method updates the inventory by removing the vehicle.
	 * @param vin
	 */
	public void updateInventory(String vin) {
	    dealership.getVehInv().deleteVehicle(vin);	
	}
}