/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class StandardRoom extends HotelRoom {

    private String bedType;
    private int maxOccupancy;

    public StandardRoom(String roomId, String roomNumber,
                         double basePrice,
                         String bedType,
                         int maxOccupancy) {

        super(roomId, roomNumber, basePrice);

        this.bedType = bedType;
        this.maxOccupancy = maxOccupancy;
    }

    @Override
    public double calculatePrice() {
        return basePrice;
    }

    @Override
    public String getDetails() {
        return "Standard Room - " + roomNumber
                + ", Bed Type: " + bedType
                + ", Max Occupancy: " + maxOccupancy;
    }
}