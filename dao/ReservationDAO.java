package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    // CREATE
    public boolean addReservation(String reservationId, String userId,
                                  String reservationDate, String checkInDate,
                                  String checkOutDate, String status,
                                  double totalAmount) {

        String sql = "INSERT INTO reservations "
                + "(reservation_id, user_id, reservation_date, check_in_date, "
                + "check_out_date, status, total_amount) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationId);
            stmt.setString(2, userId);
            stmt.setString(3, reservationDate);
            stmt.setString(4, checkInDate);
            stmt.setString(5, checkOutDate);
            stmt.setString(6, status);
            stmt.setDouble(7, totalAmount);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ
    public List<String[]> getAllReservations() {

        List<String[]> reservations = new ArrayList<>();

        String sql = "SELECT reservation_id, user_id, reservation_date, "
                + "check_in_date, check_out_date, status, total_amount "
                + "FROM reservations";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                reservations.add(new String[]{
                    rs.getString("reservation_id"),
                    rs.getString("user_id"),
                    rs.getString("reservation_date"),
                    rs.getString("check_in_date"),
                    rs.getString("check_out_date"),
                    rs.getString("status"),
                    String.valueOf(rs.getDouble("total_amount"))
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // UPDATE
    public boolean updateReservation(String reservationId, String userId,
                                     String reservationDate, String checkInDate,
                                     String checkOutDate, String status,
                                     double totalAmount) {

        String sql = "UPDATE reservations SET user_id = ?, "
                + "reservation_date = ?, check_in_date = ?, "
                + "check_out_date = ?, status = ?, total_amount = ? "
                + "WHERE reservation_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            stmt.setString(2, reservationDate);
            stmt.setString(3, checkInDate);
            stmt.setString(4, checkOutDate);
            stmt.setString(5, status);
            stmt.setDouble(6, totalAmount);
            stmt.setString(7, reservationId);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteReservation(String reservationId) {

        String sql = "DELETE FROM reservations WHERE reservation_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationId);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}