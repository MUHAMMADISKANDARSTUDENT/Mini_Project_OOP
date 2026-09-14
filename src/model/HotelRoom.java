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
        return "Room " + roomNumber + " - RM" + basePrice;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getBasePrice() {
        return basePrice;
    }
}