package src.dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReservationRoomDAO {

    // CREATE
    public boolean addReservationRoom(
            String reservationId,
            String roomId) {

        String sql = """
            INSERT INTO reservation_rooms
            (reservation_id, room_id)
            VALUES (?, ?)
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationId);
            stmt.setString(2, roomId);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
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
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    System.out.println(
                        "Reservation ID: "
                        + rs.getString("reservation_id")
                        + ", Room ID: "
                        + rs.getString("room_id")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
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
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationId);
            stmt.setString(2, roomId);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}