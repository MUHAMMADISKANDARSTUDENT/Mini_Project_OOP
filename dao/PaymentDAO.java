package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // CREATE
    public boolean addPayment(String paymentId, String reservationId,
                              String paymentDate, double amount,
                              String method, String status) {

        String sql = "INSERT INTO payments "
                + "(payment_id, reservation_id, payment_date, amount, method, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paymentId);
            stmt.setString(2, reservationId);
            stmt.setString(3, paymentDate);
            stmt.setDouble(4, amount);
            stmt.setString(5, method);
            stmt.setString(6, status);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ
    public List<String[]> getAllPayments() {

        List<String[]> payments = new ArrayList<>();

        String sql = "SELECT payment_id, reservation_id, payment_date, "
                + "amount, method, status FROM payments";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                payments.add(new String[]{
                    rs.getString("payment_id"),
                    rs.getString("reservation_id"),
                    rs.getString("payment_date"),
                    String.valueOf(rs.getDouble("amount")),
                    rs.getString("method"),
                    rs.getString("status")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payments;
    }

    // UPDATE
    public boolean updatePayment(String paymentId, String reservationId,
                                 String paymentDate, double amount,
                                 String method, String status) {

        String sql = "UPDATE payments SET reservation_id = ?, "
                + "payment_date = ?, amount = ?, method = ?, status = ? "
                + "WHERE payment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationId);
            stmt.setString(2, paymentDate);
            stmt.setDouble(3, amount);
            stmt.setString(4, method);
            stmt.setString(5, status);
            stmt.setString(6, paymentId);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deletePayment(String paymentId) {

        String sql = "DELETE FROM payments WHERE payment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paymentId);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}