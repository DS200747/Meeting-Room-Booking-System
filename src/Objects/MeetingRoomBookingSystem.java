package Objects;

import java.util.ArrayList;
import java.util.Scanner;

public class MeetingRoomBookingSystem {

    public static Scanner input = new Scanner(System.in);
    public static ArrayList<User> UserList = new ArrayList<>();
    public static ArrayList<Bookings> AllBookingsList = new ArrayList<>();
    public static ArrayList<String> CleaningTimesList = new ArrayList<>();
    public static ArrayList<String> FoodOrdersList = new ArrayList<>();
    public static String email;
    public static String password;
    public static int index;

    public static void main(String[] args) {

        /*- use the date import?
          - Need to add cleaning times and food orders to array lists
          - edit bookings
          - Update colour
          - Add getting date, time etc into different methods because wow code
          - room 1 = 2 people, room 2 = 4 people, room 3 = 8, room 4 = 15 (disabled access), room 5 = 50
          - Room object? to see if a room is available?
          - Time slots: 9am-10, (30 min clean), 10.30-11.30, (30 min cleaning), 12-1pm, (30 min cleaning), 1.30-2.30, (30 min cleaning), 3-4, (30 min cleaning), 4.30-5.30, (30 min cleaning).
          - Make GUI?
         */
        AllBookingsList = FileHandling.readAllBookingsListFile();
        UserList = FileHandling.readUserFile();
        StartUp();

    }

    public static void StartUp() {
        System.out.println("\t\u001b[34m Welcome to the meeting room booking system\n\u001b[0m");
        while (true) {
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("0 - Exit");
            int userInput = input.nextInt();

            switch (userInput) {
                case 1:
                    Login();
                    break;
                case 2:
                    Register();
                    break;
                case 0:
                    FileHandling.writeAllBookingsListFile(AllBookingsList);
                    FileHandling.writeUserFile(UserList);
                    System.exit(0);
                    break;
            }
        }
    }

    public static void Login() {
        index = GetUserIndex();
        if (index == -1) {
            System.out.println("\u001b[31mSorry, that user doesn't exist.\u001b[0m");
        } else {
            if (UserList.get(index).isStaff()) {
                StaffMainMenu();
            } else {
                MainMenu();
            }
        }
    }

    public static void Register() {
        System.out.println("What is your name?");
        String name = input.next();

        while (true) {
            System.out.println("What is your email address?");
            email = input.next();
            if (email.contains("@") && email.contains(".")) {
                break;
            } else {
                System.out.println("\u001b[31mSorry, that is an invalid email.\u001b[0m");
            }
        }

        while (true) {
            System.out.println("What will your password be? It must be atleast 6 characters long.");
            password = input.next();
            if (password.length() >= 6) {
                break;
            } else {
                System.out.println("\u001b[31mPassword must be atleast 6 characters!\u001b[0m");
            }
        }

        User newuser = new User(name, email, password, false);
        UserList.add(newuser);
        System.out.println("Thank you for registering with us, \u001b[34m" + name + "\u001b[0m");
    }

    // <editor-fold defaultstate="collapsed" desc="User Methods">
    public static int GetUserIndex() {
        System.out.println("What is your email?");
        email = input.next();
        System.out.println("What is your password?");
        password = input.next();

        for (int i = 0; i < UserList.size(); i++) {
            if (UserList.get(i).getEmail().equals(email) && UserList.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }

    public static void MainMenu() {
        System.out.println("\nWelcome back, \u001b[34m" + UserList.get(index).getName() + "\u001b[0m!");
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1 - Book a room");
            System.out.println("2 - View bookings");
            System.out.println("3 - Delete a booking");
            System.out.println("4 - Edit a booking");
            System.out.println("5 - View account details");
            System.out.println("6 - Edit account details");
            System.out.println("7 - Delete account");
            System.out.println("0 - Logout");
            int userAnswer = input.nextInt();

            switch (userAnswer) {
                case 1:
                    BookARoom();
                    break;
                case 2:
                    ViewBookings();
                    break;
                case 3:
                    DeleteBooking();
                    break;
                case 4:

                    break;
                case 5:
                    System.out.println(UserList.get(index).toString());
                    break;
                case 6:
                    EditAccount();
                    break;
                case 7:
                    DeleteAccount();
                    break;
                case 0:
                    System.out.println("Logging out...\n");
                    StartUp();
                    break;
            }
        }

    }

    public static void EditAccount() {
        System.out.println("What would you like to edit?");
        System.out.println("1 - Name");
        System.out.println("2 - Email");
        System.out.println("3 - Password");
        System.out.println("0 - Exit");
        int UserInput = input.nextInt();

        switch (UserInput) {
            case 1:
                System.out.println("What would you like to change your name to?");
                String NewName = input.next();
                UserList.get(index).setName(NewName);
                System.out.println("Your name has been changed to " + UserList.get(index).getName());
                break;
            case 2:
                while (true) {
                    System.out.println("What would you like to change your email to?");
                    String NewEmail = input.next();
                    if (email.contains("@") && email.contains(".")) {
                        UserList.get(index).setEmail(NewEmail);
                        System.out.println("Your email has been chnaged to " + UserList.get(index).getEmail());
                        break;
                    } else {
                        System.out.println("\u001b[31mSorry, that is an invalid email.\u001b[0m");
                    }
                }
                break;
            case 3:
                System.out.println("Please type in your CURRENT password");
                String password = input.next();
                if (password.equals(UserList.get(index).getPassword())) {
                    while (true) {
                        System.out.println("Please type in your new password");
                        String NewPassword = input.next();
                        if (NewPassword.length() >= 6) {
                            UserList.get(index).setPassword(NewPassword);
                            System.out.println("Your password has been changed.");
                            break;
                        } else {
                            System.out.println("\u001b[31mPassword must be atleast 6 characters!\u001b[0m");
                        }
                    }
                }
                break;
            case 0:
                break;
        }
    }

    public static void DeleteAccount() {
        System.out.println("Are you sure you would like to delete your account? This will delete any meetings you may have booked.");
        System.out.println("1 - Yes");
        System.out.println("0 - Exit");
        int UserAnswer = input.nextInt();

        if (UserAnswer == 1) {
            for (int i = 0; i < AllBookingsList.size(); i++) {
                if (AllBookingsList.get(i).getUser().equals(UserList.get(index).getEmail())) {
                    AllBookingsList.remove(i);
                }
            }
            UserList.remove(index);
            System.out.println("Your account has successfully been deleted.");
            StartUp();
        } else {
            System.out.println("Sorry, that's an invalid answer.");
        }

    }

    public static void StaffMainMenu() {
        System.out.println("\nWelcome back, \u001b[34m" + UserList.get(index).getName() + "\u001b[0m!");
        System.out.println("What would you like to do?");
        System.out.println("1 - View cleaning times");
        System.out.println("2 - View food orders and times");
        System.out.println("3 - Edit account");
        System.out.println("4 - View account details");
        System.out.println("0 - Logout");
        int staffInput = input.nextInt();

        switch (staffInput) {
            case 1:
                if (CleaningTimesList.isEmpty()) {
                    System.out.println("There are currently no cleaning slots filled");
                } else {
                    System.out.println(CleaningTimesList);
                }
                break;
            case 2:
                if (FoodOrdersList.isEmpty()) {
                    System.out.println("There are currently no food orders");
                } else {
                    System.out.println(FoodOrdersList);
                }
                break;
            case 3:
                EditAccount();
                break;
            case 4:
                System.out.println(UserList.get(index).toString());
                break;
            case 0:
                System.out.println("Logging out...\n");
                StartUp();
                break;
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Booking Methods">
    public static void BookARoom() {
        while (true) {
            int room = SetRoomMethod();
            
            System.out.println("What day would you like to book room " + room + "? DD/MM/YYYY");
            String date = input.next();

            String time = SetTimeMethod();

            
            if (CheckRoomAvailability(room, date, time) == true) {
                System.out.println("Would you like to order any food?");
                String foodCheck = input.next();
                if (foodCheck.equals("y") || foodCheck.equals("Y") || foodCheck.equals("Yes") || foodCheck.equals("yes")) {
                    System.out.println("What food would you like to order?");
                    String order = input.next();
                    double foodTime = GetAndCheckFoodTime(time);
                    System.out.println("Would you like to have any resources such as pens and paper when you arrive?");
                    String resourcesCheck = input.next();
                    if (resourcesCheck.equals("y") || resourcesCheck.equals("Y") || resourcesCheck.equals("Yes") || resourcesCheck.equals("yes")) {
                        System.out.println("What resources would you like?");
                        String resources = input.next();
                        Bookings newBooking = new Bookings(UserList.get(index).getEmail(), room, time, date, order, foodTime, resources);
                        AllBookingsList.add(newBooking);
                        System.out.println("Your booking has been made.");
                        break;
                    } else {
                        String resources = "-";
                        Bookings newBooking = new Bookings(UserList.get(index).getEmail(), room, time, date, order, foodTime, resources);
                        AllBookingsList.add(newBooking);
                        System.out.println("Your booking has been made.");
                        break;
                    }
                } else {
                    String order = "-";
                    double foodTime = 0;
                    System.out.println("Would you like to have any resources such as pens and paper when you arrive?");
                    String resourcesCheck = input.next();
                    if (resourcesCheck.equals("y") || resourcesCheck.equals("Y") || resourcesCheck.equals("Yes") || resourcesCheck.equals("yes")) {
                        System.out.println("What resources would you like?");
                        String resources = input.next();
                        Bookings newBooking = new Bookings(UserList.get(index).getEmail(), room, time, date, order, foodTime, resources);
                        AllBookingsList.add(newBooking);
                        System.out.println("Your booking has been made.");
                        break;
                    } else {
                        String resources = "-";
                        Bookings newBooking = new Bookings(UserList.get(index).getEmail(), room, time, date, order, foodTime, resources);
                        AllBookingsList.add(newBooking);
                        System.out.println("Your booking has been made.");
                        break;
                    }
                }
            } else {
                System.out.println("Sorry, this room isn't available on this date and time.");
            }
        }

    }

    public static boolean CheckRoomAvailability(int room, String date, String time) {
        if (AllBookingsList.isEmpty()) {
            return true;
        } else {
            for (int i = 0; i < AllBookingsList.size(); i++) {
                int AlreadyRoom = AllBookingsList.get(i).getRoom();
                String AlreadyDate = AllBookingsList.get(i).getDate();
                String AlreadyTime = AllBookingsList.get(i).getTime();

                if (AlreadyRoom == room && AlreadyDate.equals(date) && AlreadyTime.equals(time)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static double GetAndCheckFoodTime(String time) {
        while (true) {
            System.out.println("What time would you like this food delivered?");
            double foodTime = input.nextDouble();
            if (time.equals("9-10") && foodTime >= 9 && foodTime <= 10) {
                return foodTime;
            } else if (time.equals("10:30-11:30") && foodTime >= 10.3 && foodTime <= 11.3) {
                return foodTime;
            } else if (time.equals("12-13") && foodTime >= 12 && foodTime <= 13) {
                return foodTime;
            } else if (time.equals("13:30-14:30") && foodTime >= 13.3 && foodTime <= 14.3) {
                return foodTime;
            } else if (time.equals("15-16") && foodTime >= 15 && foodTime <= 16) {
                return foodTime;
            } else if (time.equals("16:30-17:30") && foodTime >= 16.3 && foodTime <= 17.3) {
                return foodTime;
            } else {
                System.out.println("Sorry, that is not a valid time.");
            }
        }
    }

    public static int GetAllBookingIndex() {
        email = UserList.get(index).getEmail();
        System.out.println("What is the date of your booking?");
        String date = input.next();
        System.out.println("What is the time of your booking?");
        String time = input.next();

        for (int i = 0; i < AllBookingsList.size(); i++) {
            if (AllBookingsList.get(i).getUser().equals(email) && AllBookingsList.get(i).getDate().equals(date) && AllBookingsList.get(i).getTime().equals(time)) {
                return i;
            }
        }
        return -1;
    }

    public static void EditBooking() {
        int bookingIndex = GetAllBookingIndex();

        while (true) {
            System.out.println("What would you like to edit?");
            System.out.println("1 - Date of meeting");
            System.out.println("2 - Time of meeting");
            System.out.println("3 - Room meeting is held in");
            System.out.println("4 - Food order");
            System.out.println("5 - Resources");
            System.out.println("0 - Nevermind");
            int userAnswer = input.nextInt();

            if (userAnswer == 1) {
                System.out.println("What is the new date you would like the meeting to be held?");
                String newDate = input.next();
                if (CheckRoomAvailability(AllBookingsList.get(bookingIndex).getRoom(), newDate, AllBookingsList.get(bookingIndex).getTime()) == true) {
                    AllBookingsList.get(bookingIndex).setDate(newDate);
                    System.out.println("Date successfully changed");
                } else {
                    System.out.println("Sorry, there is already a meeting with this date and time.");
                }
            } else if (userAnswer == 2) {
                String newTime = SetTimeMethod();
                
                if (newTime.equals(AllBookingsList.get(bookingIndex).getTime())) {
                    System.out.println("The time of your booking hasn't been changed.");
                } else if (CheckRoomAvailability(AllBookingsList.get(bookingIndex).getRoom(), AllBookingsList.get(bookingIndex).getDate(), newTime) == true) {
                    AllBookingsList.get(bookingIndex).setTime(newTime);
                    System.out.println("The time of your booking has been changed.");
                    break;
                } else {
                    System.out.println("Sorry, there's already a meeting with that date and time.");
                }
            } else if (userAnswer == 3) {
                int newRoom = SetRoomMethod();
                AllBookingsList.get(bookingIndex).setRoom(newRoom);
            } else if (userAnswer == 4) {
                System.out.println("What would you like to do?");
                System.out.println("1 - Delete a food order");
                System.out.println("2 - Make a new food order");
                System.out.println("3 - Change a current order");
                System.out.println("4 - Change a current order's time");
                int userReply = input.nextInt();

                switch (userReply) {
                    case 1:
                        AllBookingsList.get(bookingIndex).setFoodTime(0);
                        AllBookingsList.get(bookingIndex).setOrder("-");
                        System.out.println("Your food order has been removed.");
                        break;
                    case 2:
                        if (AllBookingsList.get(bookingIndex).getFoodTime() == 0 && AllBookingsList.get(bookingIndex).getOrder().equals("-")) {
                            System.out.println("What would you like to order");
                            String order = input.next();
                            System.out.println("What time would you like the order delivered?");

                        } else {
                            System.out.println("You already have a food order for your meeting.");
                        }
                        break;
                    case 3:
                        System.out.println("Your current order is " + AllBookingsList.get(bookingIndex).getOrder());
                        System.out.println("What would you like your new order to be?");
                        String newOrder = input.next();
                        AllBookingsList.get(bookingIndex).setOrder(newOrder);
                        System.out.println("Your order has been changed.");
                        break;
                    case 4:
                        System.out.println("Your order's current time is " + AllBookingsList.get(bookingIndex).getFoodTime());
                        System.out.println("What would you like your new time to be?");
                        double newTime = input.nextDouble();

                        break;
                }
            } else if (userAnswer == 5) {

            } else {
                break;
            }
        }
    }

    public static void DeleteBooking() {
        System.out.println("Are you sure you want to delete this booking? Once it is gone you cannot retrieve it.");
        System.out.println("1 - Yes");
        System.out.println("0 - No");
        int UserAnswer = input.nextInt();

        if (UserAnswer == 1) {
            int bookingIndex = GetAllBookingIndex();
            if (bookingIndex == -1) {
                System.out.println("Sorry, this booking doesn't exist");
            } else {
                AllBookingsList.remove(bookingIndex);
                System.out.println("Booking has been deleted");
            }
        }
    }

    public static void ViewBookings() {
        if (AllBookingsList.isEmpty()) {
            System.out.println("Sorry, there are no bookings at this time.");
        } else {
            for (int i = 0; i < AllBookingsList.size(); i++) {
                if (AllBookingsList.get(i).getUser().equals(UserList.get(index).getEmail())) {
                    System.out.println(AllBookingsList.get(i));
                }
            }
        }
    }
    
    public static int SetRoomMethod(){
            while (true) {
                System.out.println("Do you need disabled access for your room?");
                String disabledAccess = input.next();
                if (disabledAccess.equals("Y") || disabledAccess.equals("y") || disabledAccess.equals("yes") || disabledAccess.equals("Yes")) {
                    System.out.println("Room 4 has been selected.");
                    return 4;
                } else {
                    System.out.println("How many people would you like to book a room for?");
                    int peopleNum = input.nextInt();
                    if (peopleNum == 1 || peopleNum == 2) {
                        System.out.println("Room 1 has been selected");
                        return 1;
                    } else if (peopleNum == 3 || peopleNum == 4) {
                        System.out.println("Room 2 has been selected");
                        return 2;
                    } else if (peopleNum >= 5 && peopleNum <= 8) {
                        System.out.println("Room 3 has been selected");
                        return 3;
                    } else if (peopleNum >= 9 && peopleNum <= 15) {
                        System.out.println("Room 4 has been selcted");
                        return 4;
                    } else if (peopleNum >= 16 && peopleNum <= 50) {
                        System.out.println("Room 5 has been selected");
                        return 5;
                    } else if (peopleNum > 50) {
                        System.out.println("Sorry, our rooms cannot hold that many people!");
                    } else {
                        System.out.println("Sorry, that's an invalid answer");
                    }
                }
            }
    }
    
    public static String SetTimeMethod(){
        while (true) {
                System.out.println("What time slot would you like to book your room for?");
                System.out.println("1 - 9am to 10am");
                System.out.println("2 - 10:30am to 11:30 am");
                System.out.println("3 - 12pm to 13pm");
                System.out.println("4 - 13:30pm to 14:30pm");
                System.out.println("5 - 15pm to 16pm");
                System.out.println("6 - 16:30pm to 17:30pm");
                int timeNum = input.nextInt();

                if (timeNum == 1) {
                    return "9-10";
                } else if (timeNum == 2) {
                    return "10:30-11:30";
                } else if (timeNum == 3) {
                    return "12-13";
                } else if (timeNum == 4) {
                    return "13:30-14:30";
                } else if (timeNum == 5) {
                    return "15-16";
                } else if (timeNum == 6) {
                    return "16:30-17:30";
                } else {
                    System.out.println("Sorry, that isn't a valid input");
                }
            }
    }

    // </editor-fold>
}
