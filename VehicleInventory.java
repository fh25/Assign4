package assign4;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * VehicleInventory class that implements Serializable.
 * @author fhj
 */
public class VehicleInventory implements Serializable {
  
	/**
	 * A set of all the vehicles in the inventory. We don't want to allow 
     * duplicate vehicle entries but care about the order in which the vehicles 
     * were added in the inventory, hence, we opted for using a LinkedHashSet 
     * collection type. In addition, we anticipate a big number of vehicles 
     * will have to be removed from the middle of the set as sale transactions 
     * are completed. The remove operation in a LinkedHashSet takes constant, 
     * O(1) time.
	 */
	private LinkedHashSet<Vehicle> vehicles;

    /**
     * Default Constructor.
     */
    public VehicleInventory() {
      vehicles = new LinkedHashSet<>();
    }

    /**
     * Returns the vehicles LinkedHashSet.
     * @return 
     */
    public LinkedHashSet<Vehicle> getVehicles() {
      return vehicles;
    }
    
    /**
     * This method addPassengerCar, adds a Car to the vehicles LinkedHashSet.
     * @param vin
     * @param make
     * @param model
     * @param year
     * @param mileage
     * @param price
     * @param bodyStyle 
     */
    public void addPassengerCar (String vin, String make, String model, int year, 
            int mileage, float price, String bodyStyle) {
      
        PassengerCar car = new PassengerCar();
        
        car.setVin(vin);
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setMileage(mileage);
        car.setPrice(price);
        car.setBodyStyle(bodyStyle);
        vehicles.add(car);   
    }
    
    /**
     * This method addTruck, adds a Truck to the vehicles LinkedHashSet.
     * @param vin
     * @param make
     * @param model
     * @param year
     * @param mileage
     * @param price
     * @param maxLoad
     * @param length 
     */
    public void addTruck (String vin, String make, String model, int year, 
            int mileage, float price, float maxLoad, float length) {
      
        Truck truck = new Truck();
        
        truck.setVin(vin);
        truck.setMake(make);
        truck.setModel(model);
        truck.setYear(year);
        truck.setMileage(mileage);
        truck.setPrice(price);
        truck.setMaxLoadWeight(maxLoad);
        truck.setLength(length);
        vehicles.add(truck);   
    }
    
    /**
     * This method addMotorcycle, adds a Motorcycle to the vehicles LinkedHashSet.
     * @param vin
     * @param make
     * @param model
     * @param year
     * @param mileage
     * @param price
     * @param type
     * @param engineCC 
     */
    public void addMotorcycle (String vin, String make, String model, int year, 
            int mileage, float price, String type, int engineCC) {
      
        Motorcycle cycle = new Motorcycle();
        
        cycle.setVin(vin);
        cycle.setMake(make);
        cycle.setModel(model);
        cycle.setYear(year);
        cycle.setMileage(mileage);
        cycle.setPrice(price);
        cycle.setType(type);
        cycle.setDisplacement(engineCC);
        vehicles.add(cycle);   
    }

	/**
     * This method deleteVehicle deletes a vehicle based on their vin.
     * @param vin 
     */
	public void deleteVehicle(String vin) {
        for (Vehicle v : vehicles ) {
          if (v.getVin().equals(vin))
           vehicles.remove(v); 
        }
	}

	/**
	 * This method updateVehicle updates a vehicle based on the vin.
	 * @param v
     * @throws assign4.BadInputException
	 */
	public void updateVehicle(String v) throws BadInputException {
        int option = 0;
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Select an option to update:\n"
          + "1. VIN Number.\n"
          + "2. Make.\n"
          + "3. Model.\n"
          + "4. Year.\n"
          + "5. Mileage.\n"
          + "6. Price.\n"
          + "7. Exit\n");
        System.out.print("Your choice: ");
        option = scan.nextInt();
        scan.nextLine();
      
        for (Vehicle vehicle : vehicles) {
          
          if (vehicle.getVin().equals(v)) {
            
              if (option == 1) {
                  System.out.print("Enter new VIN number: ");
                  String newVin = scan.nextLine();
                  if (vehicle.getVin().equals(newVin)) {
                     throw new BadInputException("Same VIN, enter new VIN.");
                  }
                  vehicle.setVin(newVin);
                  
              } else if (option == 2) {
                  System.out.print("Enter new Make: ");
                  String newMake = scan.nextLine();
                  vehicle.setMake(newMake);
                  
              } else if (option == 3) {
                  System.out.print("Enter new Model: ");
                  String newModel = scan.nextLine();
                  vehicle.setModel(newModel);
                  
              } else if (option == 4) {
                  System.out.print("Enter new Year: ");
                  int newYear = scan.nextInt();
                  vehicle.setYear(newYear);
                  
              } else if (option == 5) {
                  System.out.print("Enter new Mileage: ");
                  int newMileage = scan.nextInt();
                  if (newMileage < 0) {
                     throw new BadInputException("Mileage can't be negative");
                  }
                  scan.nextLine();
                  vehicle.setMileage(newMileage);
                  
              } else if (option == 6) {
                  System.out.print("Enter new price: ");
                  float newPrice = scan.nextFloat();
                  if (newPrice < 0.0f)
                     throw new BadInputException("Price can't be negative.");
                  scan.nextLine();

                  vehicle.setPrice(newPrice);
                  
              } else if (option == 7) {
                  return;
              }
          }
        }
	}

	/**
	 * This method searchInventory searches an inventory based on vin.
	 * @param vin 
     * @throws java.lang.InterruptedException 
	 */
	public void searchInventory(String vin) throws InterruptedException {
      
        Date currentDate = new Date(System.currentTimeMillis());
      
        System.out.println(currentDate);
        BusyThread busy = new BusyThread();
        busy.start();
        
        Thread.sleep(2500);
        
		for (Vehicle v : vehicles) {
           if (vin.equals(v.getVin())) {
               System.out.println("Vehicle found.");
               v.print();
               busy.setBusy(false);
           }  
        }
        System.out.println("Vehicle Not Found.");
        busy.setBusy(false);
	}
    
    /**
     * This method printVehicles prints all Vehicles.
     */
    public void printVehicles() {
      System.out.print("\n");
      for (Vehicle v : vehicles) {
        if (v instanceof PassengerCar) {
          v.print();
        } else if (v instanceof Truck) {
          v.print();
        } else if (v instanceof Motorcycle) {
          v.print();
        }
      }
    }
    
    /**
     * This method verifyVehicle validates a vehicle is valid based on the vin.
     * @param vin
     * @return 
     */
    public Boolean verifyVehicle(String vin) {
      for (Vehicle v : vehicles) {
        if (v.getVin().equals(vin)) {
          return true;
        }
      }
      return false;
    }
}