package src.dao;

import src.database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.model.Payment;

public class PaymentDAO {

    // CREATE
    public boolean addPayment(Payment payment) {

        String sql = """
            INSERT INTO payments
            (
                payment_id,
                reservation_id,
                payment_date,
                amount,
                method,
                status
            )
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, payment.getPaymentId());
            stmt.setString(2, payment.getReservationId());
            stmt.setString(3, payment.getPaymentDate());
            stmt.setDouble(4, payment.getAmount());
            stmt.setString(5, payment.getMethod());
            stmt.setString(6, payment.getStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // READ ALL
    public List<Payment> getAllPayments() {

        List<Payment> payments = new ArrayList<>();

        String sql = """
            SELECT payment_id, reservation_id,
                   payment_date, amount, method, status
            FROM payments
            ORDER BY payment_id
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Payment payment = new Payment(
                    rs.getString("payment_id"),
                    rs.getString("reservation_id"),
                    rs.getString("payment_date"),
                    rs.getDouble("amount"),
                    rs.getString("method"),
                    rs.getString("status")
                );

                payments.add(payment);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return payments;
    }

    // READ ONE
    public Payment getPaymentById(String paymentId) {

        String sql = """
            SELECT payment_id, reservation_id,
                   payment_date, amount, method, status
            FROM payments
            WHERE payment_id = ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paymentId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return new Payment(
                        rs.getString("payment_id"),
                        rs.getString("reservation_id"),
                        rs.getString("payment_date"),
                        rs.getDouble("amount"),
                        rs.getString("method"),
                        rs.getString("status")
                    );
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    // UPDATE
    public boolean updatePayment(Payment payment) {

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

            stmt.setString(1, payment.getReservationId());
            stmt.setString(2, payment.getPaymentDate());
            stmt.setDouble(3, payment.getAmount());
            stmt.setString(4, payment.getMethod());
            stmt.setString(5, payment.getStatus());
            stmt.setString(6, payment.getPaymentId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
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

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}