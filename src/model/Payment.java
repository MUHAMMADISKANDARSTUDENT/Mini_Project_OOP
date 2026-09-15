/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

public class Payment {

    private String paymentId;
    private String reservationId;
    private String paymentDate;
    private double amount;
    private String method;
    private String status;

    public Payment(
            String paymentId,
            String reservationId,
            String paymentDate,
            double amount,
            String method,
            String status) {

        this.paymentId = paymentId;
        this.reservationId = reservationId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }
}