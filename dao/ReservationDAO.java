package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAO {

    // CREATE
    public void addReservation(String reservationId, String userId,
                               String reservationDate, String checkInDate,
                               String checkOutDate, String status,
                               double totalAmount) {

        String sql = "INSERT INTO reservations " +
                "(reservation_id, user_id, reservation_date, check_in_date, " +
                "check_out_date, status, total_amount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reservationId);
            pstmt.setString(2, userId);
            pstmt.setString(3, reservationDate);
            pstmt.setString(4, checkInDate);
            pstmt.setString(5, checkOutDate);
            pstmt.setString(6, status);
            pstmt.setDouble(7, totalAmount);

            pstmt.executeUpdate();
            System.out.println("Reservation added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public void getAllReservations() {

        String sql = "SELECT * FROM reservations";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getString("reservation_id") + " | " +
                        rs.getString("user_id") + " | " +
                        rs.getString("reservation_date") + " | " +
                        rs.getString("check_in_date") + " | " +
                        rs.getString("check_out_date") + " | " +
                        rs.getString("status") + " | " +
                        rs.getDouble("total_amount")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updateReservation(String reservationId, String userId,
                                   String reservationDate, String checkInDate,
                                   String checkOutDate, String status,
                                   double totalAmount) {

        String sql = "UPDATE reservations SET " +
                "user_id = ?, reservation_date = ?, check_in_date = ?, " +
                "check_out_date = ?, status = ?, total_amount = ? " +
                "WHERE reservation_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, reservationDate);
            pstmt.setString(3, checkInDate);
            pstmt.setString(4, checkOutDate);
            pstmt.setString(5, status);
            pstmt.setDouble(6, totalAmount);
            pstmt.setString(7, reservationId);

            pstmt.executeUpdate();
            System.out.println("Reservation updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public boolean deleteReservation(String reservationId) {

        String sql = "DELETE FROM reservations WHERE reservation_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reservationId);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}