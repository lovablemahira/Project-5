import java.io.*;
import java.util.ArrayList;

/**
 * <h1>Database Manager</h1>
 *
 * Used to locally save and retrieve data.
 *
 * @author (jacks668, David Jackson), (morri417, Mahira Morris)
 *
 * @version 12/9/18
 */
public class DatabaseManager {

    /**
     * Creates an ArrayList of Vehicles from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of vehicles
     */
    public static ArrayList<Vehicle> loadVehicles(File file) {
        FileReader fr;
        BufferedReader br;
        String line = "";
        ArrayList<Vehicle> vroom = new ArrayList<>();
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Vehicle toAdd = null;
                if (data[0].equalsIgnoreCase("Drone")) {
                    toAdd = new Drone(data[1], Double.parseDouble(data[2]));
                } else if (data[0].equalsIgnoreCase("Truck")) {
                    toAdd = new Truck(data[1], Double.parseDouble(data[2]));
                } else if (data[0].equalsIgnoreCase("Cargo Plane")) {
                    toAdd = new CargoPlane(data[1], Double.parseDouble(data[2]));
                }
                vroom.add(toAdd);
            }
            fr.close();
            br.close();
        } catch (FileNotFoundException x) {
            return vroom;
        } catch (IOException y) {
            System.out.println("There was an error reading your vehicle list file");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Data for vehicle in your vehicle list file was inputted improperly");
        }
        return vroom;
    }





    /**
     * Creates an ArrayList of Packages from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of packages
     */
    public static ArrayList<Package> loadPackages(File file) {
        FileReader fr;
        BufferedReader br;
        String line = "";
        ArrayList<Package> packingPeanuts = new ArrayList<>();
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                String[] information = line.split(",");
                ShippingAddress toWhere = new ShippingAddress(information[4], information[5],
                        information[6], information[7], Integer.parseInt(information[8]));
                Package packToAdd = new Package(information[0], information[1],
                        Double.parseDouble(information[2]), Double.parseDouble(information[3]),
                        toWhere);
                packingPeanuts.add(packToAdd);
            }
            fr.close();
            br.close();
        } catch (FileNotFoundException x) {
            return packingPeanuts;
        } catch (IOException y) {
            System.out.println("Sorry, there was an error reading from your package list file");
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Data for package in your package list file was inputted improperly");
        }
        return packingPeanuts;
    }






    /**
     * Returns the total Profits from passed text file. If the file does not exist 0
     * will be returned.
     *
     * @param file file where profits are stored
     * @return profits from file
     */
    public static double loadProfit(File file) {
        FileReader fr;
        BufferedReader br;
        double profit = 0.0;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            profit = Double.parseDouble(br.readLine());
            fr.close();
            br.close();
        } catch (FileNotFoundException x) {
            return profit;
        } catch (IOException | NumberFormatException y) {
            System.out.println("There was an error reading from your profits file");
        }
        return profit;
    }





    /**
     * Returns the total number of packages shipped stored in the text file. If the
     * file does not exist 0 will be returned.
     *
     * @param file file where number of packages shipped are stored
     * @return number of packages shipped from file
     */
    public static int loadPackagesShipped(File file) {
        FileReader fr;
        BufferedReader br;
        int numPack = 0;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            numPack = Integer.parseInt(br.readLine());
            fr.close();
            br.close();
        } catch (FileNotFoundException x) {
            return 0;
        } catch (IOException | NumberFormatException y) {
            System.out.println("There was an error reading from your packages shipped file");
        }
        return numPack;
    }




    /**
     * Returns whether or not it was Prime Day in the previous session. If file does
     * not exist, returns false.
     *
     * @param file file where prime day is stored
     * @return whether or not it is prime day
     */
    public static boolean loadPrimeDay(File file) {
        FileReader fr;
        BufferedReader br;
        boolean prime = false;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            prime = Integer.parseInt(br.readLine()) == 1;
            fr.close();
            br.close();
        } catch (FileNotFoundException x) {
            return prime;
        } catch (IOException | NumberFormatException y) {
            System.out.println("There was an error reading from your prime day file");
        }
        return prime;
    }


    /**
     * Saves (writes) vehicles from ArrayList of vehicles to file in CSV format one vehicle per line.
     * Each line (vehicle) has following fields separated by comma in the same order.
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     *
     * @param file     File to write vehicles to
     * @param vehicles ArrayList of vehicles to save to file
     */
    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
        PrintWriter pW;

        try {
            pW = new PrintWriter(file);
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i) instanceof Truck) {
                    pW.write("Truck");
                } else if (vehicles.get(i) instanceof Drone) {
                    pW.write("Drone");
                } else {
                    pW.write("Cargo Plane");
                }
                pW.write((vehicles.get(i)).getLicensePlate() + ",");
                pW.write((vehicles.get(i)).getMaxWeight() + ",");
                pW.write("\n");
            }
            pW.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("We couldn't find the given file.");
        }
    }




    /**
     * Saves (writes) packages from ArrayList of package to file in CSV format one package per line.
     * Each line (package) has following fields separated by comma in the same order.
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * @param file     File to write packages to
     * @param packages ArrayList of packages to save to file
     */
    public static void savePackages(File file, ArrayList<Package> packages) {
        PrintWriter pW;

        try {
            pW = new PrintWriter(file);
            for (int i = 0; i < packages.size(); i++) {
                pW.write((packages.get(i)).getID() + ",");
                pW.write((packages.get(i)).getProduct() + ",");
                pW.write((packages.get(i)).getWeight() + ",");
                pW.write((packages.get(i)).getPrice() + ",");
                pW.write(((packages.get(i)).getDestination()).getName() + ",");
                pW.write(((packages.get(i)).getDestination()).getAddress() + ",");
                pW.write(((packages.get(i)).getDestination()).getCity() + ",");
                pW.write(((packages.get(i)).getDestination()).getState() + ",");
                pW.write(((packages.get(i)).getDestination()).getZipCode() + ",");
                pW.write("\n");
            }
            pW.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("We couldn't find the given file.");
        }
    }




    /**
     * Saves profit to text file.
     *
     * @param file   File to write profits to
     * @param profit Total profits
     */

    public static void saveProfit(File file, double profit) {
        PrintWriter pW;

        try {
            pW = new PrintWriter(file);
            pW.write(Double.toString(profit));
            pW.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("We couldn't find the given file.");
        }
    }





    /**
     * Saves number of packages shipped to text file.
     *
     * @param file      File to write profits to
     * @param nPackages Number of packages shipped
     */

    public static void savePackagesShipped(File file, int nPackages) {
        PrintWriter pW;

        try {
            pW = new PrintWriter(file);
            pW.write(Integer.toString(nPackages));
            pW.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("We couldn't find the given file.");
        }
    }






    /**
     * Saves status of prime day to text file. If it is primeDay "1" will be
     * writtern, otherwise "0" will be written.
     *
     * @param file     File to write profits to
     * @param primeDay Whether or not it is Prime Day
     */

    public static void savePrimeDay(File file, boolean primeDay) {
        PrintWriter pW;

        try {
            pW = new PrintWriter(file);
            if (primeDay == true) {
                pW.write("1");
            } else {
                pW.write("0");
            }
            pW.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("We couldn't find the given file.");
        }
    }
}