package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    // CREATE
    public boolean addRoom(
            String roomId,
            String roomNumber,
            String roomType,
            String bedType,
            double basePrice,
            String extraService,
            int maxOccupancy) {

        String sql = """
            INSERT INTO rooms
            (room_id, room_number, room_type, base_price,
             bed_type, max_occupancy, extra_service)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, roomId);
            stmt.setString(2, roomNumber);
            stmt.setString(3, roomType);
            stmt.setDouble(4, basePrice);
            stmt.setString(5, bedType);
            stmt.setInt(6, maxOccupancy);
            stmt.setString(7, extraService);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ ALL
    public List<String[]> getAllRooms() {

        List<String[]> rooms = new ArrayList<>();

        String sql = """
            SELECT room_id, room_number, room_type, base_price,
                   bed_type, max_occupancy, extra_service
            FROM rooms
            ORDER BY room_id
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                String[] room = {
                    rs.getString("room_id"),
                    rs.getString("room_number"),
                    rs.getString("room_type"),
                    rs.getString("bed_type"),
                    String.valueOf(rs.getDouble("base_price")),
                    rs.getString("extra_service"),
                    String.valueOf(rs.getInt("max_occupancy"))
                };

                rooms.add(room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // READ ONE
    public String[] getRoomById(String roomId) {

        String sql = """
            SELECT room_id, room_number, room_type, base_price,
                   bed_type, max_occupancy, extra_service
            FROM rooms
            WHERE room_id = ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, roomId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return new String[]{
                        rs.getString("room_id"),
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getString("bed_type"),
                        String.valueOf(rs.getDouble("base_price")),
                        rs.getString("extra_service"),
                        String.valueOf(rs.getInt("max_occupancy"))
                    };
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE
    public boolean updateRoom(
            String roomId,
            String roomNumber,
            String roomType,
            String bedType,
            double basePrice,
            String extraService,
            int maxOccupancy) {

        String sql = """
            UPDATE rooms
            SET room_number = ?,
                room_type = ?,
                base_price = ?,
                bed_type = ?,
                max_occupancy = ?,
                extra_service = ?
            WHERE room_id = ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, roomNumber);
            stmt.setString(2, roomType);
            stmt.setDouble(3, basePrice);
            stmt.setString(4, bedType);
            stmt.setInt(5, maxOccupancy);
            stmt.setString(6, extraService);
            stmt.setString(7, roomId);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteRoom(String roomId) {

        String sql = """
            DELETE FROM rooms
            WHERE room_id = ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, roomId);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}