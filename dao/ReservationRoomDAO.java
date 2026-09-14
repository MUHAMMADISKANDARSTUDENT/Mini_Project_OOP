package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservationRoomDAO {

    // CREATE - link a room to a reservation
    public boolean addReservationRoom(String reservationId, String roomId) {

        String sql = "INSERT INTO reservation_rooms "
                + "(reservation_id, room_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationId);
            stmt.setString(2, roomId);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ - get rooms linked to a reservation
    public List<String[]> getRoomsByReservation(String reservationId) {

        List<String[]> rooms = new ArrayList<>();

        String sql = "SELECT reservation_id, room_id "
                + "FROM reservation_rooms WHERE reservation_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    rooms.add(new String[]{
                        rs.getString("reservation_id"),
                        rs.getString("room_id")
                    });
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // DELETE - remove a room from a reservation
    public boolean deleteReservationRoom(String reservationId, String roomId) {

        String sql = "DELETE FROM reservation_rooms "
                + "WHERE reservation_id = ? AND room_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationId);
            stmt.setString(2, roomId);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}