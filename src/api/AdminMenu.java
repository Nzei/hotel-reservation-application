package api;

import model.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static AdminResource adminResource = AdminResource.getSingleInstance();
    private static Scanner scanner = new Scanner(System.in);


    public static void adminMenu() throws ParseException {
        try{
            System.out.println();
            System.out.println("Welcome Admin, please select an option below");
            System.out.println("----------------------------");
            System.out.println();
            System.out.println("1. See all customers");
            System.out.println("2. See all rooms");
            System.out.println("3. See all reservations");
            System.out.println("4. Add a room");
            System.out.println("5. Back to main menu");
            int optionSelected = scanner.nextInt();
            optionsChosen(optionSelected);
        }
        catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
        }
        finally {
            adminMenu();
        }

    }

    public static void optionsChosen(int selection) throws ParseException {
        switch(selection) {
            case 1 :
                seeAllCustomers();

            case 2 :
                seeAllRooms();

            case 3 :
                seeAllReservations();

            case 4 :
                addRoom();

            case 5 :
                backToMainMenu();

            default:
                System.out.println("Invalid entry, please select a valid option");
                backToMainMenu();
        }
    }

    private static void seeAllCustomers() throws ParseException {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if(customers.isEmpty()){
            System.out.println("There are no customers in the list");
        }
        customers.forEach((customer) -> System.out.println(customer + "\n"));
        adminMenu();
    }

    private static void seeAllRooms() throws ParseException {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if(rooms.isEmpty()){
            System.out.println("There are no current rooms");
        }
        rooms.forEach(iRoom -> System.out.println(iRoom + "\n"));
        adminMenu();
    }

    private static void seeAllReservations() throws ParseException {
        Collection<Reservation> reservations = adminResource.displayAllReservations();
        if(reservations.isEmpty()){
            System.out.println("There are currently no reservations");
        }
        reservations.forEach(System.out::println);
        adminMenu();
    }

    private static void addRoom() throws ParseException {
        System.out.println();
        System.out.println("Please enter the room number");
        String roomNumber = scanner.next();

        System.out.println();
        System.out.println("Please enter the room price");
        double roomPrice = scanner.nextDouble();
        System.out.println();
        System.out.println("Please select 1 for a Single room and 2 for a Double room");
        RoomType roomType = scanner.nextInt() == 1 ? RoomType.SINGLE : RoomType.DOUBLE;

        IRoom room = new Room(roomNumber,roomPrice,roomType);
        List<IRoom> roomList = new ArrayList<>();
        roomList.add(room);
        System.out.println();
        System.out.println("Do you want to add another room ? Select Y for yes and N for no");
        System.out.println();
        String choice = scanner.next().toUpperCase();
        switch(choice){
            case "Y" :
                adminResource.addRoom(roomList);
                addRoom();
                break;

            case "N" :
                adminResource.addRoom(roomList);
                System.out.println();
                System.out.println("Thank you");
                adminMenu();
                break;

            default:
                adminResource.addRoom(roomList);
                System.out.println("Invalid entry. Please choose either Y or N");
                adminMenu();
        }
    }

    private static void backToMainMenu() throws ParseException {
        MainMenu.mainMenu();
    }
}
