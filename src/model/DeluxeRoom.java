/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class DeluxeRoom extends HotelRoom {

    private String bedType;
    private int maxOccupancy;
    private String extraService;

    public DeluxeRoom(String roomId, String roomNumber, double basePrice,
                      String bedType, int maxOccupancy, String extraService) {
        super(roomId, roomNumber, basePrice);
        this.bedType = bedType;
        this.maxOccupancy = maxOccupancy;
        this.extraService = extraService;
    }

    @Override
    public double calculatePrice() {
        return basePrice * 1.20;
    }

    public String getDetails() {
        return "Deluxe Room - " + roomNumber
                + ", Bed Type: " + bedType
                + ", Max Occupancy: " + maxOccupancy
                + ", Extra Service: " + extraService;
    }
}