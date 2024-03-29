package assign4;

/**
 * Motorcycle class that extends Vehicle
 * @author fhj
 */
public class Motorcycle extends Vehicle {

    /**
     * private String type to store type of motorcycle.
     */
	private String type;
    
    /**
     * private int displacement to store the motorcycle engine size.
     */
    private int displacement;

    /**
     * Default constructor.
     */
    public Motorcycle() {
        this.type = "";
        this.displacement = 0;
    }

    /**
     * Constructor used to initialize the class fields of the class with the
     * provided values.
     * @param vin
     * @param make
     * @param model
     * @param year
     * @param mileage
     * @param price
     * @param type
     * @param displacement
     */
    public Motorcycle(String vin, String make, String model, int year, 
            int mileage, float price, String type, int displacement) {
        super(vin, make, model, year, mileage, price);
        this.type = type;
        this.displacement = displacement;
    }

    /**
     * Get the motorcycle type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Get the motorcycle type.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the motorcycle displacement.
     * @return
     */
    public int getDisplacement() {
        return displacement;
    }

    /**
     * Set the motorcycle displacement.
     * @param displacement
     */
    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }
    
    /**
     * Print the attributes of the truck, in a formatted fashion.
     */
    @Override
    public void print() {
        System.out.format("| %12s | %12s | %8s | %8s | %6d | %9d | %10.2f | %9s %5dcc | %n", 
                "Motorcycle", vin, make, model, year, mileage, price, type, displacement);
    }

    /**
     * Overrides toString
     * @return 
     */
    @Override
    public String toString() {
        return "Motorcycle{" + "vin=" + vin + ", make=" + make + 
                ", model=" + model + ", year=" + year + ", mileage=" + mileage + 
                ", price=" + price + ", type=" + type + 
                ", displacement=" + displacement + '}';
    }

}