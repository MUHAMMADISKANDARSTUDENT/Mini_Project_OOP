/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelreservation.model;

public class HotelRoom {

    private int roomId;
    private String roomNumber;
    private double price;
    private boolean available;

    public HotelRoom(int roomId, String roomNumber,
                     double price, boolean available) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.price = price;
        this.available = available;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double calculatePrice(int nights) {
        return price * nights;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber +
               " - RM" + price +
               " - Available: " + available;
    }
}
