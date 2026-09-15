/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class HotelRoom {

    protected String roomId;
    protected String roomNumber;
    protected double basePrice;

    public HotelRoom(String roomId, String roomNumber, double basePrice) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.basePrice = basePrice;
    }

    public double calculatePrice() {
        return basePrice;
    }

    public String getDetails() {
        return "Room " + roomNumber
                + " - RM" + String.format("%.2f", basePrice);
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public static double getBasePriceByBedType(String bedType) {

        switch (bedType) {
            case "Single":
                return 100.00;

            case "Double":
                return 120.00;

            case "Queen":
                return 140.00;

            case "King":
                return 160.00;

            default:
                return 100.00;
        }
    }
}