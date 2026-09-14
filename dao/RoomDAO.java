package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAO {

    // CREATE
    public void addRoom(String roomId, String roomNumber, String roomType,
                        double basePrice, String bedType, int maxOccupancy,
                        String extraService) {

        String sql = "INSERT INTO rooms " +
                "(room_id, room_number, room_type, base_price, bed_type, max_occupancy, extra_service) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, roomId);
            pstmt.setString(2, roomNumber);
            pstmt.setString(3, roomType);
            pstmt.setDouble(4, basePrice);
            pstmt.setString(5, bedType);
            pstmt.setInt(6, maxOccupancy);
            pstmt.setString(7, extraService);

            pstmt.executeUpdate();
            System.out.println("Room added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public void getAllRooms() {

        String sql = "SELECT * FROM rooms";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getString("room_id") + " | " +
                        rs.getString("room_number") + " | " +
                        rs.getString("room_type") + " | " +
                        rs.getDouble("base_price") + " | " +
                        rs.getString("bed_type") + " | " +
                        rs.getInt("max_occupancy") + " | " +
                        rs.getString("extra_service")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updateRoom(String roomId, String roomNumber, String roomType,
                           double basePrice, String bedType, int maxOccupancy,
                           String extraService) {

        String sql = "UPDATE rooms SET " +
                "room_number = ?, room_type = ?, base_price = ?, " +
                "bed_type = ?, max_occupancy = ?, extra_service = ? " +
                "WHERE room_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, roomNumber);
            pstmt.setString(2, roomType);
            pstmt.setDouble(3, basePrice);
            pstmt.setString(4, bedType);
            pstmt.setInt(5, maxOccupancy);
            pstmt.setString(6, extraService);
            pstmt.setString(7, roomId);

            pstmt.executeUpdate();
            System.out.println("Room updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public boolean deleteRoom(String roomId) {

        String sql = "DELETE FROM rooms WHERE room_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, roomId);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}