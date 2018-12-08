import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Warehouse</h1>
 */

public class Warehouse {
	final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File(folderPath + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;
    static String menu = "==========Options==========" +
                    "\n1) Add Package" +
                    "\n2) Add Vehicle" +
                    "\n3) Activate Prime Day" +
                    "\n4) Send Vehicle" +
                    "\n5) Print Statistics" +
                    "\n6) Exit" +
                    "\n===========================";

    /**
     * Main Method
     * 
     * @param args list of command line arguements
     */
    public static void main(String[] args) {
        //Variables\\
        Scanner userInput = new Scanner(System.in);
    	//TODO
    	
    	//1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
    	
    	
    	
    	//2) Show menu and handle user inputs
        System.out.println(menu);
        String selection = userInput.nextLine();

        while (selection != "6") {
            switch (selection) {
                case "1":
                    //Variables\\
                    String id;
                    String name;
                    double weight;
                    double price;
                    String buyerName;
                    String address;
                    String city;
                    String state;
                    int zipCode;

                    //Code\\
                    System.out.println("Enter Package ID:");
                    id  = userInput.nextLine();
                    System.out.println("Enter Product Name:");
                    name = userInput.nextLine();
                    //---------------------------------May have to handle errors
                    System.out.println("Enter Weight:");
                    weight = userInput.nextDouble();
                    System.out.println("Enter Price:");
                    price = userInput.nextDouble();
                    System.out.println("Enter Buyer Name:");
                    userInput.nextLine();
                    buyerName = userInput.nextLine();
                    System.out.println("Enter Address:");
                    address = userInput.nextLine();
                    System.out.println("Enter City:");
                    city = userInput.nextLine();
                    System.out.println("Enter State:");
                    state = userInput.nextLine();
                    System.out.println("Enter ZIP Code:");
                    zipCode = userInput.nextInt();
                    userInput.nextLine();
                    ShippingAddress shippingAddress = new ShippingAddress(buyerName, address, city, state, zipCode);
                    Package boxPackage = new Package(id, name, weight, price, shippingAddress);
                    String label = boxPackage.shippingLabel();
                    System.out.println(label);
                    break;
                case "2":
                    System.out.println("Vehicle Options:");
                    System.out.println("1) Truck");
                    System.out.println("2) Drone");
                    System.out.println("3) Cargo Plane");
                    String vehicle = userInput.nextLine();
                    System.out.println("Enter License Plate No.:");
                    //---------------------------------May have to handle errors
                    String license = userInput.nextLine();
                    System.out.println("Enter Maximum Carry Weight:");
                    double maxWeight = userInput.nextDouble();
                    userInput.nextLine();

                    switch (vehicle) {
                        case "1":
                            Vehicle truck = new Truck(license, maxWeight);
                            break;
                        case "2":
                            Vehicle drone = new Drone(license, maxWeight);
                            break;
                        case "3":
                            Vehicle plane = new CargoPlane(license, maxWeight);
                            break;
                        default:
                            //---------------------------------May have to handle errors
                            System.out.println("Sorry, not an option.");
                    }
                    break;
                case "3":
                    if (menu == ("==========Options==========" +
                        "\n1) Add Package" +
                        "\n2) Add Vehicle" +
                        "\n3) Deactivate Prime Day" +
                        "\n4) Send Vehicle" +
                        "\n5) Print Statistics" +
                        "\n6) Exit" +
                        "\n===========================")) {
                        menu = "==========Options==========" +
                                "\n1) Add Package" +
                                "\n2) Add Vehicle" +
                                "\n3) Activate Prime Day" +
                                "\n4) Send Vehicle" +
                                "\n5) Print Statistics" +
                                "\n6) Exit" +
                                "\n===========================";
                    } else {
                        menu = "==========Options==========" +
                                "\n1) Add Package" +
                                "\n2) Add Vehicle" +
                                "\n3) Deactivate Prime Day" +
                                "\n4) Send Vehicle" +
                                "\n5) Print Statistics" +
                                "\n6) Exit" +
                                "\n===========================";
                    }
                    //---------------------------------NOT DONE, NEED TO UPDATE ALL PRICES
                    break;
                case "4":
                    System.out.println("Options:");
                    System.out.println("1) Send Truck");
                    System.out.println("2) Send Drone");
                    System.out.println("3) Send Cargo Plane");
                    System.out.println("4) Send First Available");
                    String sendVehicle = userInput.nextLine();
                    String zipcodeOption;

                    switch (sendVehicle) {
                        case "1":
                            System.out.println("ZIP Code Options:");
                            System.out.println("1) Send to first ZIP Code");
                            System.out.println("2) Send to mode of ZIP Codes");
                            zipcodeOption = userInput.nextLine();

                            switch (zipcodeOption) {
                                case "1":
                                    break;
                                case "2":
                                    break;
                                default:
                                    System.out.println("Sorry, that's not an option.");
                                    break;
                            }
                            break;
                        case "2":
                            System.out.println("ZIP Code Options:");
                            System.out.println("1) Send to first ZIP Code");
                            System.out.println("2) Send to mode of ZIP Codes");
                            zipcodeOption = userInput.nextLine();

                            switch (zipcodeOption) {
                                case "1":
                                    break;
                                case "2":
                                    break;
                                default:
                                    System.out.println("Sorry, that's not an option.");
                                    break;
                            }
                            break;
                        case "3":
                            System.out.println("ZIP Code Options:");
                            System.out.println("1) Send to first ZIP Code");
                            System.out.println("2) Send to mode of ZIP Codes");
                            zipcodeOption = userInput.nextLine();

                            switch (zipcodeOption) {
                                case "1":
                                    break;
                                case "2":
                                    break;
                                default:
                                    System.out.println("Sorry, that's not an option.");
                                    break;
                            }
                            break;
                        default:
                            //---------------------------------May have to handle errors
                            System.out.println("Error: No vehicles of selected type are available.");
                    }
                    break;
                case "5":
                    System.out.println("==========Statistics==========");
                    System.out.println("Profits:");
                    System.out.println("Packages Shipped:");
                    System.out.println("Packages in Warehouse:");
                    System.out.println("==============================");
                    break;
                case "6":
                    DatabaseManager dbm = new DatabaseManager();

//                    dbm.saveVehicles();
//                    dbm.saveProfit();
//                    dbm.savePackagesShipped();
//                    dbm.savePrimeDay();
                    return;
                default:
                    System.out.println("Error: Option not available.");
                    break;
            }
            System.out.println(menu);
            selection = userInput.nextLine();
        }

        //3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager
    	
    
    }


}
