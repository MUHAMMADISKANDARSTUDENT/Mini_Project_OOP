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
    private String userId;
    private String roomId;
    private String reservationDate;
    private String checkInDate;
    private String checkOutDate;
    private String status;
    private double totalAmount;

    public Reservation(
            String reservationId,
            String userId,
            String roomId,
            String reservationDate,
            String checkInDate,
            String checkOutDate,
            String status,
            double totalAmount) {

        this.reservationId = reservationId;
        this.userId = userId;
        this.roomId = roomId;
        this.reservationDate = reservationDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}