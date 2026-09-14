/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelreservation.model;

public class DeluxeRoom extends HotelRoom {

    public DeluxeRoom(int roomId, String roomNumber,
                      double price, boolean available) {

        super(roomId, roomNumber, price, available);
    }

    @Override
    public double calculatePrice(int nights) {
        double basePrice = getPrice() * nights;

        // Example: 10% extra for deluxe room
        return basePrice * 1.10;
    }

    @Override
    public String toString() {
        return "Deluxe Room - " + getRoomNumber()
                + " - RM" + getPrice();
    }
}
