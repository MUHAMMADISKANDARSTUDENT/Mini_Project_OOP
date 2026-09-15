package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // CREATE
    public boolean addPayment(
            String paymentId,
            String reservationId,
            String paymentDate,
            double amount,
            String method,
            String status) {

        String sql = """
            INSERT INTO payments
            (payment_id, reservation_id, payment_date,
             amount, method, status)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paymentId);
            stmt.setString(2, reservationId);
            stmt.setString(3, paymentDate);
            stmt.setDouble(4, amount);
            stmt.setString(5, method);
            stmt.setString(6, status);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ ALL
    public List<String[]> getAllPayments() {

        List<String[]> payments = new ArrayList<>();

        String sql = """
            SELECT payment_id, reservation_id, payment_date,
                   amount, method, status
            FROM payments
            ORDER BY payment_id
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                String[] payment = {
                    rs.getString("payment_id"),
                    rs.getString("reservation_id"),
                    rs.getString("payment_date"),
                    String.valueOf(rs.getDouble("amount")),
                    rs.getString("method"),
                    rs.getString("status")
                };

                payments.add(payment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payments;
    }

    // READ ONE
    public String[] getPaymentById(String paymentId) {

        String sql = """
            SELECT payment_id, reservation_id, payment_date,
                   amount, method, status
            FROM payments
            WHERE payment_id = ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paymentId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return new String[]{
                        rs.getString("payment_id"),
                        rs.getString("reservation_id"),
                        rs.getString("payment_date"),
                        String.valueOf(rs.getDouble("amount")),
                        rs.getString("method"),
                        rs.getString("status")
                    };
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE
    public boolean updatePayment(
            String paymentId,
            String reservationId,
            String paymentDate,
            double amount,
            String method,
            String status) {

        String sql = """
            UPDATE payments
            SET reservation_id = ?,
                payment_date = ?,
                amount = ?,
                method = ?,
                status = ?
            WHERE payment_id = ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationId);
            stmt.setString(2, paymentDate);
            stmt.setDouble(3, amount);
            stmt.setString(4, method);
            stmt.setString(5, status);
            stmt.setString(6, paymentId);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deletePayment(String paymentId) {

        String sql = """
            DELETE FROM payments
            WHERE payment_id = ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paymentId);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}