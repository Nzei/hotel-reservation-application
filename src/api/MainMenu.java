package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {

    private static CustomerService customerService = CustomerService.getSingleInstance();
    private static HotelResource hotelResource = HotelResource.getSingleInstance();
    private static final String DATE_FORMAT = "DD/MM/YYYY";
    private static Customer customer;
    private static Scanner scanner = new Scanner(System.in);
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^(.+)@(.+).com$", Pattern.CASE_INSENSITIVE);


    public static void mainMenu() throws ParseException {
        try{
            System.out.println("Welcome, please select an option below");
            System.out.println("----------------------------");
            System.out.println();

            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");

            Scanner scanner = new Scanner(System.in);
            int selection = scanner.nextInt();
            optionChosen(selection);
        }
        catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
        }
        finally {
            mainMenu();
        }
    }

    public static void optionChosen(int selection) throws ParseException {
        switch (selection) {
            case 1:
                findAndReserveRoom();

            case 2:
                seeReservations();

            case 3:
                createAccount();

            case 4:
                adminView();

            case 5:
                System.exit(0);

            default:
                System.out.println("Please enter a valid input from the main menu options");
                mainMenu();
        }
    }

    private static void findAndReserveRoom() throws ParseException {

        try {
            System.out.println();
            System.out.println("Please do you have an account with us?  Enter Y for yes and N for no.");
            System.out.println();

            String input = scanner.next().toUpperCase();

            switch (input) {
                case "Y":
                    System.out.println("Please enter your registered valid email address");
                    String userEmail = scanner.next();
                    System.out.println();
                    customer = customerService.getCustomer(userEmail);
                    System.out.println();
                    System.out.println(customer);
                    System.out.println();
                    System.out.println("Below is a list of our available rooms");
                    Collection<IRoom> rooms = hotelResource.getAllRooms();
                    rooms.forEach(iRoom -> System.out.println(iRoom + "\n"));
                    System.out.println();
                    System.out.println("Please enter the corresponding number of the room you would like to book");
                    System.out.println();
                    String roomNumber = scanner.next();
                    System.out.println("Room number " + roomNumber);
                    System.out.println();
                    System.out.println("Please enter the dates you would like to book the room for in the format DD/MM/YYYY");
                    System.out.println();
                    System.out.println("Check in date: ");
                    String checkInDate = scanner.next();
                    Date checkIn = new SimpleDateFormat(DATE_FORMAT).parse(checkInDate);
                    System.out.println(checkIn);
                    System.out.println();
                    System.out.println("Check out date: ");
                    String checkOutDate = scanner.next();
                    Date checkOut = new SimpleDateFormat(DATE_FORMAT).parse(checkOutDate);
                    System.out.println(checkOut);
                    System.out.println();
                    Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);
                    IRoom selectedRoom = hotelResource.getRoom(roomNumber);
                    hotelResource.bookARoom(userEmail,selectedRoom,checkIn,checkOut);
                    System.out.println();
                    mainMenu();
                    break;

                case "N":
                    System.out.println("Please register to make a reservation");
                    System.out.println();
                    createAccount();
                    System.out.println();

                default:
                    System.out.println("Invalid entry, Please enter either Y for yes or N for no");
                    mainMenu();
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
        }
        finally {
            mainMenu();
        }
    }

    private static Collection<Reservation> seeReservations() throws ParseException {
        String email = "";
        try {
            System.out.println("Please enter your email");
            email = scanner.next();
            if (email.isEmpty() || email.isBlank()) {
                return null;
            }
            if(customerService.getAllCustomers().contains(email)){
                System.out.println(customerService.getCustomer(email));
            }
            else{
                System.out.println("Customer does not exist or invalid email address");
            }
        } catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
        } finally {
            mainMenu();
        }
        return hotelResource.getCustomerReservations(email);
    }

    private static void createAccount() throws ParseException {
        try{
            System.out.println();
            System.out.println("Please enter your first name");
            String firstName = scanner.next();
            System.out.println();
            System.out.println("Please enter your last name");
            String lastName = scanner.next();
            System.out.println();
            System.out.println("Please enter your email in the format: name@domain.com");
            String email = scanner.next();
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            while(!matcher.matches()){
                System.out.println("Invalid email address format");
                System.out.println();
                System.out.println("Please enter your email in the format: name@domain.com");
                email = scanner.next();
                Matcher matcher2 = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                if(matcher2.matches()){
                    break;
                }
            }
            System.out.println();
            hotelResource.createACustomer(email, firstName, lastName);
            System.out.println("Thank you for registering with us, Please select an option from the menu below");
            System.out.println();
            mainMenu();
        }
        catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
        }
        finally {
            mainMenu();
        }
    }

    private static void adminView() throws ParseException {
        AdminMenu.adminMenu();
    }

}
