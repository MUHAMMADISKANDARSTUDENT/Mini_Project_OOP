package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRoomDAO {

    // CREATE - Add a room to a reservation
    public void addReservationRoom(String reservationId, String roomId) {

        String sql = "INSERT INTO reservation_rooms " +
                     "(reservation_id, room_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reservationId);
            pstmt.setString(2, roomId);

            pstmt.executeUpdate();
            System.out.println("Reservation-room added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ - Get rooms belonging to a reservation
    public void getRoomsByReservation(String reservationId) {

        String sql = "SELECT room_id FROM reservation_rooms " +
                     "WHERE reservation_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reservationId);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    System.out.println(
                            "Reservation: " + reservationId +
                            " | Room: " + rs.getString("room_id")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE - Remove a room from a reservation
    public boolean deleteReservationRoom(String reservationId, String roomId) {

        String sql = "DELETE FROM reservation_rooms " +
                     "WHERE reservation_id = ? AND room_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reservationId);
            pstmt.setString(2, roomId);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}