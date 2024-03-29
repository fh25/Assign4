package assign4;

/**
 * Truck class that extends Vehicle.
 * @author fhj
 */
public class Truck extends Vehicle {

	/**
     * The maximum allowed load weight of the truck.
     */
    private float maxLoadWeight;
    
    /**
     * The length of the truck (in feet).
     */
    private float length;

    /**
     * Default Constructor.
     */
    public Truck() {
        this.maxLoadWeight = 0.0f;
        this.length = 0.0f;
    }

    /**
     * Constructor that initializes a Truck object with the given params.
     * @param vin
     * @param make
     * @param model
     * @param year
     * @param mileage
     * @param price
     * @param maxLoadWeight
     * @param length
     */
    public Truck(String vin, String make, String model, int year, int mileage, 
            float price, float maxLoadWeight, float length) {
        super(vin, make, model, year, mileage, price);
        this.maxLoadWeight = maxLoadWeight;
        this.length = length;
    }

    /**
     *
     * @return
     */
    public float getMaxLoadWeight() {
        return maxLoadWeight;
    }

    /**
     *
     * @param maxLoadWeight
     */
    public void setMaxLoadWeight(float maxLoadWeight) {
        this.maxLoadWeight = maxLoadWeight;
    }

    /**
     *
     * @return
     */
    public float getLength() {
        return length;
    }

    /**
     *
     * @param length
     */
    public void setLength(float length) {
        this.length = length;
    }
    
    /**
     * Print the attributes of the truck, in a formatted fashion.
     */
    @Override
    public void print() {
        System.out.format("| %12s | %12s | %8s | %8s | %6d | %9d | %10.2f | %7.2flb %5.2fft | %n", 
                "Truck", vin, make, model, year, mileage, price, maxLoadWeight, length);
    }

}