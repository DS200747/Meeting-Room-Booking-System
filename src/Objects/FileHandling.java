
package Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileHandling {
    
    // <editor-fold defaultstate="collapsed" desc="User Files">  
    public static String folderDirectory = System.getProperty("user.dir") + "\\UserList.txt";

    public static void writeUserFile(ArrayList<User> UserList) {

        try {
            FileWriter writeToFile = new FileWriter(folderDirectory, false);
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < UserList.size(); i++) {
                printToFile.println(UserList.get(i).toString());
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }


    public static ArrayList<User> readUserFile() {
        ArrayList<User> UserList = new ArrayList<>();
        String lineFromFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(folderDirectory));
            while ((lineFromFile = read.readLine()) != null) {
                String[] userDetails = lineFromFile.split(", ");

                User newUser = new User(userDetails[0], userDetails[1], userDetails[2], Boolean.parseBoolean(userDetails[3]));
                UserList.add(newUser);
            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return UserList;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="All Bookings Files">  
    public static String folderPathway = System.getProperty("user.dir") + "\\AllBookingsList.txt";

    public static void writeAllBookingsListFile(ArrayList<Bookings> AllBookingsList) {

        try {
            FileWriter writeToFile = new FileWriter(folderPathway, false);
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < AllBookingsList.size(); i++) {
                printToFile.println(AllBookingsList.get(i).toString());
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public static ArrayList<Bookings> readAllBookingsListFile() {
        ArrayList<Bookings> AllBookingsList = new ArrayList<>();
        String lineFromFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(folderPathway));
            while ((lineFromFile = read.readLine()) != null) {
                String[] AllBookingsDetails = lineFromFile.split(", ");

                Bookings newBooking = new Bookings(AllBookingsDetails[0], Integer.parseInt(AllBookingsDetails[1]), AllBookingsDetails[2], AllBookingsDetails[3], AllBookingsDetails[4], Double.parseDouble(AllBookingsDetails[5]), AllBookingsDetails[6]);
                AllBookingsList.add(newBooking);
            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return AllBookingsList;
    }
    
     // </editor-fold>
}
