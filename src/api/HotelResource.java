package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static HotelResource hotelResource = HotelResource.getSingleInstance();
    private static CustomerService customerService = CustomerService.getSingleInstance();
    private static ReservationService reservationService = ReservationService.getInstance();

    private HotelResource() {

    }

    public static HotelResource getSingleInstance() {
        if(hotelResource == null){
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public Customer getCustomer(String email) {
        if(email.isEmpty() || email.isBlank()) {
            return null;
        }
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        if(email.isBlank() || email.isBlank() || firstName.isEmpty() || firstName.isBlank() || lastName.isEmpty() || lastName.isBlank()) {
            System.out.println("Invalid Email or Last Name or First Name");
        }
        customerService.addCustomer(email,firstName,lastName);
    }

    public IRoom getRoom(String roomNumber) {
        if(roomNumber.isBlank() || roomNumber.isEmpty()){
            return null;
        }
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = CustomerService.getSingleInstance().getCustomer(customerEmail);
        if(customer == null){
            return null;
        }
        return reservationService.reserveARoom(customer,room,checkInDate,checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations (String customerEmail) {
        Customer customer = CustomerService.getSingleInstance().getCustomer(customerEmail);
        if(customer == null) {
            return null;
        }
        return reservationService.getCustomerReservation(customer);
    }

    public Collection <IRoom> findARoom (Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

    public Collection <IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }
}
