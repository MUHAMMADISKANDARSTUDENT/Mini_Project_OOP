package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRoomDAO {

    // CREATE
    public boolean addReservationRoom(String reservationId, String roomId) {

        String sql = """
                INSERT INTO reservation_rooms
                (reservation_id, room_id)
                VALUES (?, ?)
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reservationId);
            pstmt.setString(2, roomId);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error adding reservation room: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // READ
    public void getRoomsByReservation(String reservationId) {

        String sql = """
                SELECT reservation_id, room_id
                FROM reservation_rooms
                WHERE reservation_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reservationId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                System.out.println(
                        "Reservation ID: "
                        + rs.getString("reservation_id")
                        + " | Room ID: "
                        + rs.getString("room_id")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error reading reservation rooms: "
                    + e.getMessage()
            );
        }
    }

    // DELETE
    public boolean deleteReservationRoom(
            String reservationId,
            String roomId) {

        String sql = """
                DELETE FROM reservation_rooms
                WHERE reservation_id = ?
                AND room_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reservationId);
            pstmt.setString(2, roomId);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting reservation room: "
                    + e.getMessage()
            );

            return false;
        }
    }
}