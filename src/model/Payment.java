/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

public class Payment {

    private String paymentId;
    private LocalDate paymentDate;
    private double amount;
    private String method;
    private String status;

    public Payment(String paymentId, LocalDate paymentDate,
                   double amount, String method) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.method = method;
        this.status = "Pending";
    }

    public boolean processPayment() {
        if (amount > 0) {
            status = "Paid";
            return true;
        }

        status = "Failed";
        return false;
    }

    public boolean refund() {
        if (status.equals("Paid")) {
            status = "Refunded";
            return true;
        }

        return false;
    }

    public String getDetails() {
        return "Payment ID: " + paymentId
                + ", Date: " + paymentDate
                + ", Amount: RM" + amount
                + ", Method: " + method
                + ", Status: " + status;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}