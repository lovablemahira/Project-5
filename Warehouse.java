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

    public static void printStatisticsReport(double profits, int packagesShipped, int numberOfPackages) {
        int length = (Double.toString(profits)).length();
        int padding = 20 - (length - 3);
        System.out.println(padding);
        System.out.println("==========Statistics==========");
        System.out.printf("Profits:%-" + padding + "s$%.2f%n", "", profits);
        System.out.printf("Packages Shipped:%16d\n", packagesShipped);
        System.out.printf("Packages in Warehouse:%11d\n", numberOfPackages);
        System.out.println("==============================");
    }


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

        ArrayList<Package> pAtWarehouse = DatabaseManager.loadPackages(PACKAGE_FILE);
        ArrayList<Vehicle> vAtWarehouse = DatabaseManager.loadVehicles(VEHICLE_FILE);
        double profit = DatabaseManager.loadProfit(PROFIT_FILE);
        int numPackagesShipped = DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
        boolean primeDay = DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);
        ArrayList<Vehicle> vOnDelivery = new ArrayList<>();

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
                    id = userInput.nextLine();
                    System.out.println("Enter Product Name:");
                    name = userInput.nextLine();
                    //---------------------------------May have to handle errors
                    System.out.println("Enter Weight:");
                    weight = userInput.nextDouble();
                    System.out.println("Enter Price:");
                    price = userInput.nextDouble();
                    if (primeDay == true) {
                        price = price - (price * PRIME_DAY_DISCOUNT);
                    }
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
                            vAtWarehouse.add(truck);
                            break;
                        case "2":
                            Vehicle drone = new Drone(license, maxWeight);
                            vAtWarehouse.add(drone);
                            break;
                        case "3":
                            Vehicle plane = new CargoPlane(license, maxWeight);
                            vAtWarehouse.add(plane);
                            break;
                        default:
                            //---------------------------------May have to handle errors
                            System.out.println("Sorry, not an option.");
                    }
                    break;
                case "3":
                    double newPrice;
                    if (menu.equals("==========Options==========" +
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
                        primeDay = false;
                        for (int i = 0; i < pAtWarehouse.size(); i++) {
                            newPrice = (pAtWarehouse.get(i)).getPrice();
                            (pAtWarehouse.get(i)).setPrice(newPrice / 0.85);
                        }
                    } else {
                        menu = "==========Options==========" +
                                "\n1) Add Package" +
                                "\n2) Add Vehicle" +
                                "\n3) Deactivate Prime Day" +
                                "\n4) Send Vehicle" +
                                "\n5) Print Statistics" +
                                "\n6) Exit" +
                                "\n===========================";
                        primeDay = true;
                        for (int i = 0; i < pAtWarehouse.size(); i++) {
                            newPrice = (pAtWarehouse.get(i)).getPrice();
                            (pAtWarehouse.get(i)).setPrice(newPrice * 0.85);
                        }

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
                            int frequentZIP = 0;
                            int occurences = 0;
                            int[] occurencesArray = new int[pAtWarehouse.size()];

                            System.out.println("ZIP Code Options:");
                            System.out.println("1) Send to first ZIP Code");
                            System.out.println("2) Send to mode of ZIP Codes");
                            zipcodeOption = userInput.nextLine();

                            switch (zipcodeOption) {
                                case "1":
                                    break;
                                case "2":
                                    for (int i = 0; i < pAtWarehouse.size(); i++) {
                                        occurences = 0;
                                        for (int j = 0; j < pAtWarehouse.size(); j++) {
                                            if (((pAtWarehouse.get(i)).getDestination()).getZipCode() == ((pAtWarehouse.get(j)).getDestination()).getZipCode()) {
                                                occurences++;
                                            }
                                        }
                                        occurencesArray[i] = occurences;
                                    }
                                    for (int i = 0; i < occurencesArray.length - 1; i++) {
                                        if (occurencesArray[i] < occurencesArray[i + 1]) {
                                            frequentZIP = i + 1;
                                        }
                                    }
                                    ((pAtWarehouse.get(frequentZIP)).getDestination()).getZipCode();
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
                                    for (int i = 0; i < pAtWarehouse.size(); i++) {
                                        occurences = 0;
                                        for (int j = 0; j < pAtWarehouse.size(); j++) {
                                            if (((pAtWarehouse.get(i)).getDestination()).getZipCode() == ((pAtWarehouse.get(j)).getDestination()).getZipCode()) {
                                                occurences++;
                                            }
                                        }
                                        occurencesArray[i] = occurences;
                                    }
                                    for (int i = 0; i < occurencesArray.length - 1; i++) {
                                        if (occurencesArray[i] < occurencesArray[i + 1]) {
                                            frequentZIP = i + 1;
                                        }
                                    }
                                    ((pAtWarehouse.get(frequentZIP)).getDestination()).getZipCode();
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
                                    for (int i = 0; i < pAtWarehouse.size(); i++) {
                                        occurences = 0;
                                        for (int j = 0; j < pAtWarehouse.size(); j++) {
                                            if (((pAtWarehouse.get(i)).getDestination()).getZipCode() == ((pAtWarehouse.get(j)).getDestination()).getZipCode()) {
                                                occurences++;
                                            }
                                        }
                                        occurencesArray[i] = occurences;
                                    }
                                    for (int i = 0; i < occurencesArray.length - 1; i++) {
                                        if (occurencesArray[i] < occurencesArray[i + 1]) {
                                            frequentZIP = i + 1;
                                        }
                                    }
                                    ((pAtWarehouse.get(frequentZIP)).getDestination()).getZipCode();
                                    break;
                                default:
                                    System.out.println("Sorry, that's not an option.");
                                    break;
                            }
                            break;
                        case "4":
                            System.out.println("ZIP Code Options:");
                            System.out.println("1) Send to first ZIP Code");
                            System.out.println("2) Send to mode of ZIP Codes");
                            zipcodeOption = userInput.nextLine();

                            switch (zipcodeOption) {
                                case "1":
                                    break;
                                case "2":
                                    for (int i = 0; i < pAtWarehouse.size(); i++) {
                                        occurences = 0;
                                        for (int j = 0; j < pAtWarehouse.size(); j++) {
                                            if (((pAtWarehouse.get(i)).getDestination()).getZipCode() == ((pAtWarehouse.get(j)).getDestination()).getZipCode()) {
                                                occurences++;
                                            }
                                        }
                                        occurencesArray[i] = occurences;
                                    }
                                    for (int i = 0; i < occurencesArray.length - 1; i++) {
                                        if (occurencesArray[i] < occurencesArray[i + 1]) {
                                            frequentZIP = i + 1;
                                        }
                                    }
                                    ((pAtWarehouse.get(frequentZIP)).getDestination()).getZipCode();
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
                    printStatisticsReport(profit, numPackagesShipped, pAtWarehouse.size());
                    break;
                case "6":
                    DatabaseManager dbm = new DatabaseManager();
                    dbm.savePackages(PACKAGE_FILE, pAtWarehouse);
                    dbm.saveVehicles(VEHICLE_FILE, vAtWarehouse);
                    dbm.saveProfit(PROFIT_FILE, profit);
                    dbm.savePackagesShipped(N_PACKAGES_FILE, numPackagesShipped);
                    dbm.savePrimeDay(PRIME_DAY_FILE, primeDay);
                    return;
                default:
                    System.out.println("Error: Option not available.");
                    break;
            }
            System.out.println(menu);
            selection = userInput.nextLine();
        }
    }
}
