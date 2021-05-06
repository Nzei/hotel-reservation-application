package model;

public class FreeRoom extends Room {


    public FreeRoom(Double price) {
        price = 0.0;
    }

    @Override
    public String toString() {
        return "The price of the room in this class is : " ;
    }
}
