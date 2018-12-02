import java.util.ArrayList;


/**
 * <h1>CargoPlane</h1> Represents a Cargo Plane
 */
public class CargoPlane extends Vehicle {
    final double GAS_RATE = 2.33;
    private int range = 0;

    /**
     * Default Constructor
     */
    //============================================================================
    //
    public CargoPlane() {
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
    public CargoPlane(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    }
    
    //============================================================================

    /**
     * Overides its superclass method. Instead, after each iteration, the range will
     * increase by 10.
     *
     * @param warehousePackages List of packages to add from
     */
    @Override
    public void fill(ArrayList<Package> warehousePackages) {
        int increment = 0;
        double currentWeight = getCurrentWeight();
        double maxWeight = getMaxWeight();
        while (warehousePackages.size() != 0 && currentWeight < maxWeight) {
            int amountThatCanFit = 0;
            for (int i = 0; i < warehousePackages.size(); i++) {
                if (currentWeight + warehousePackages.get(i).getWeight() <= maxWeight) {
                    amountThatCanFit++;
                    if (Math.abs(warehousePackages.get(i).getDestination().getZipCode() - getZipDest()) <= increment) {
                        currentWeight += warehousePackages.get(i).getWeight();
                        addPackage(warehousePackages.get(i));
                        warehousePackages.remove(i);
                        i--;
                    }
                }
            }
            if (warehousePackages.size() == 0 || amountThatCanFit == 0) {
                break;
            }
            increment += 10;
        }
        range = increment;
    }

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Cargo Plane.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 2.33)
     * </p>
     */
    @Override
    public double getProfit() {
        double revenue = 0.0;
        for (Package pkg : getPackages()) {
            revenue += pkg.getPrice();
        }
        return revenue - range * GAS_RATE;
    }

    /**
     * Generates a String of the Cargo Plane report. Cargo plane report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in cargo plane</li>
     * </ul>
     *
     * @return Cargo Plane Report
     */
    @Override
    public String report() {
        String report = "==========Cargo Plane Report==========\n" +
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