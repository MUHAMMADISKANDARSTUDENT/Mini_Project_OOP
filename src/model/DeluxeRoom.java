/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class DeluxeRoom extends HotelRoom {

    private String bedType;
    private int maxOccupancy;
    private double extraCharge;

    private static final double EXTRA_CHARGE = 80.00;

    public DeluxeRoom(String roomId, String roomNumber,
                      double basePrice,
                      String bedType,
                      int maxOccupancy) {

        super(roomId, roomNumber, basePrice);

        this.bedType = bedType;
        this.maxOccupancy = maxOccupancy;
        this.extraCharge = EXTRA_CHARGE;
    }

    @Override
    public double calculatePrice() {
        return basePrice + extraCharge;
    }

    @Override
    public String getDetails() {
        return "Deluxe Room - " + roomNumber
                + ", Bed Type: " + bedType
                + ", Max Occupancy: " + maxOccupancy
                + ", Extra Charge: RM"
                + String.format("%.2f", extraCharge);
    }

    public double getExtraCharge() {
        return extraCharge;
    }
}