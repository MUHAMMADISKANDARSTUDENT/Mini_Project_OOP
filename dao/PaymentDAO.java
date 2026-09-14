package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {

    // CREATE
    public void addPayment(String paymentId, String reservationId,
                           String paymentDate, double amount,
                           String method, String status) {

        String sql = "INSERT INTO payments " +
                "(payment_id, reservation_id, payment_date, amount, method, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, paymentId);
            pstmt.setString(2, reservationId);
            pstmt.setString(3, paymentDate);
            pstmt.setDouble(4, amount);
            pstmt.setString(5, method);
            pstmt.setString(6, status);

            pstmt.executeUpdate();
            System.out.println("Payment added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public void getAllPayments() {

        String sql = "SELECT * FROM payments";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getString("payment_id") + " | " +
                        rs.getString("reservation_id") + " | " +
                        rs.getString("payment_date") + " | " +
                        rs.getDouble("amount") + " | " +
                        rs.getString("method") + " | " +
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updatePayment(String paymentId, String reservationId,
                              String paymentDate, double amount,
                              String method, String status) {

        String sql = "UPDATE payments SET " +
                "reservation_id = ?, payment_date = ?, amount = ?, " +
                "method = ?, status = ? " +
                "WHERE payment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reservationId);
            pstmt.setString(2, paymentDate);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, method);
            pstmt.setString(5, status);
            pstmt.setString(6, paymentId);

            pstmt.executeUpdate();
            System.out.println("Payment updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public boolean deletePayment(String paymentId) {

        String sql = "DELETE FROM payments WHERE payment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, paymentId);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}