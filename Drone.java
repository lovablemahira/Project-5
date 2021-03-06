import java.util.ArrayList;

/**
 * <h1>Drone</h1> Represents a Drone
 *
 * @author (jacks668, David Jackson), (morri417, Mahira Morris)
 *
 * @version 12/9/18
 */

public class Drone extends Vehicle {

    final private double gasRate = 1.33;
    /**
     * Default Contructor 
     */
    //============================================================================
    //
    public Drone() {
        super();
    }
    
    //============================================================================

    /**
     * Constructor
     * 
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight that the vehicle can hold
     */
    //============================================================================
    //
    public Drone(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    }
    
    //============================================================================

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */
    /**
     * Returns the profits generated by the packages currently in the Truck.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 1.33)
     * </p>
     */
    @Override
    public double getProfit() {
        double revenue = 0.0;
        for (Package pkg : getPackages()) {
            revenue += pkg.getPrice();
        }
        return revenue - (getRange() - 1) * this.gasRate;
    }

    /**
     * Generates a String of the Drone report. Drone report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in truck</li>
     * </ul>
     * 
     * @return Truck Report
     */
    @Override
    public String report() {
        String report = "==========Drone Report==========\n" +
                "License Plate No.: " + getLicensePlate() + "\n" +
                "Destination: " + getZipDest() + "\n" +
                String.format("Weight Load: %.2f/%.2f\n", getCurrentWeight(), getMaxWeight()) +
                String.format("Net Profit: $%.2f\n", this.getProfit()) +
                "=====Shipping Labels=====\n";
        for (Package pkg : getPackages()) {
            report += pkg.shippingLabel();
        }
        report += "==============================\n";
        return report;
    }
    
   

}
