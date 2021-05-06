package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    private static CustomerService customerService = CustomerService.getSingleInstance();
    private static ReservationService reservationService = ReservationService.getInstance();
    private static AdminResource adminResource = AdminResource.getSingleInstance();

    private AdminResource(){

    }

    public static AdminResource getSingleInstance() {
        if(adminResource == null){
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms){
        for(IRoom room: rooms){
            reservationService.addRoom(room);
        }
    }

    public Collection <IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection <Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public Collection<Reservation> displayAllReservations(){
        return reservationService.printAllReservation();
    }
}
