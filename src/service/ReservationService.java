package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {

    private static ReservationService reservationService = ReservationService.getInstance();
    private static Collection <IRoom> roomList = new ArrayList<>();
    private static Collection <Reservation> reservations = new ArrayList<>();

    private ReservationService() {

    }

    public static ReservationService getInstance() {
        if(reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room){
        roomList.add(room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room : roomList) {
            if(room.getRoomNumber().equals(roomId)){
                System.out.println("Here is the room you wanted : " + room.toString());
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return null;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        Collection<IRoom> roomsForReserve = new ArrayList<>();
        if(reservations.isEmpty()){
            return roomList;
        }
        else{
            for(Reservation reservation: reservations) {
                if(checkInDate.before(reservation.getCheckInDate()) && checkInDate.after(reservation.getCheckOutDate()) || checkOutDate.before(reservation.getCheckInDate()) && checkOutDate.after(reservation.getCheckInDate())) {
                    for(IRoom iroom : roomList){
                        if(!reservation.getRoom().equals(roomList)){
                            roomsForReserve.add(iroom);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Collection <Reservation> getCustomerReservation(Customer customer) {
        if(reservations.isEmpty()){
            return null;
        }
        else {
            if(CustomerService.getSingleInstance().getCustomer(customer.getEmail()).equals(customer.getEmail())){
                return reservations;
            }
        }

        return null;
    }

    public Collection getAllRooms(){
        return roomList;
    }

    public Collection<Reservation> printAllReservation() {
        return reservations;
    }



}
