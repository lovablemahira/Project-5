import java.io.File;
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
    final static String menu = "==========Options==========" +
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
        String selection = "1";
    	//TODO
    	
    	//1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
    	
    	
    	
    	//2) Show menu and handle user inputs
        while (selection != "6") {
            System.out.println(menu);
            selection = userInput.nextLine();
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
                    //May have to handle errors
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
                    ShippingAddress shippingAddress = new ShippingAddress(buyerName, address, city, state, zipCode);
                    Package boxPackage = new Package(id, name, weight, price, shippingAddress);
                    String label = boxPackage.shippingLabel();
                    System.out.println(label);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Error: Option not available.");
                    break;
            }
        }

        //3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager
    	
    
    }


}
