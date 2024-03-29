package assign4;

/**
 * PassengerCar class that extends Vehicle
 * @author fhj
 */
public class PassengerCar extends Vehicle {

    /**
     * private String bodyStyle to store the body style of a vehilce.
     */
	private String bodyStyle;
    
    /**
     * Default constructor. 
     */
    public PassengerCar() {
        bodyStyle = "";
    }

    /**
     * Constructor that initializes a PassengerCar object with the given params.
     * @param vin The Vehicle Identification Number (VIN) of the vehicle.
     * @param make The make of the vehicle.
     * @param model The model of the vehicle.
     * @param year The production year of the vehicle.
     * @param mileage The mileage counter of the vehicle.
     * @param price The sale price of the vehicle.
     * @param bodyStyle The body style of the car.
     */
    public PassengerCar(String vin, String make, String model, int year, 
            int mileage, float price, String bodyStyle) {
        super(vin, make, model, year, mileage, price);
        this.bodyStyle = bodyStyle;
    }

    /**
     * Get the body style of the car.
     * @return
     */
    public String getBodyStyle() {
        return bodyStyle;
    }

    /**
     * Set the body style of the car.
     * @param bodyStyle
     */
    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }
    
    /**
     * Print the attributes of the passenger car, in a formatted fashion.
     */
    @Override
    public void print() {
        System.out.format("| %12s | %12s | %8s | %8s | %6d | %9d | %10.2f | %17s | %n", 
                "Car", vin, make, model, year, mileage, price, bodyStyle);
    }

}