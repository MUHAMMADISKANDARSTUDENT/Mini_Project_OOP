/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Reservation {

    private String reservationId;
    private LocalDate reservationDate;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;
    private double totalAmount;
    private ArrayList<HotelRoom> rooms;

    public Reservation(String reservationId, LocalDate reservationDate,
                       LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = "Pending";
        this.totalAmount = 0.0;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(HotelRoom room) {
        rooms.add(room);
    }

    public void removeRoom(HotelRoom room) {
        rooms.remove(room);
    }

    public void createReservation() {
        status = "Confirmed";
        calculateTotal();
    }

    public void cancelReservation() {
        status = "Cancelled";
    }

    public double calculateTotal() {

        long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

        totalAmount = 0.0;

        for (HotelRoom room : rooms) {
            totalAmount += room.calculatePrice() * nights;
        }

        return totalAmount;
    }

    public String getDetails() {
        return "Reservation ID: " + reservationId
                + ", Check-in: " + checkInDate
                + ", Check-out: " + checkOutDate
                + ", Status: " + status
                + ", Total: RM" + totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}