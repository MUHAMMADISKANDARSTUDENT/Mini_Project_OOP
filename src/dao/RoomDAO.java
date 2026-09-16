import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    // =====================================================
    // ADD ROOM
    // =====================================================

    public boolean addRoom(HotelRoom room) {

        String sql = """
            INSERT INTO rooms
            (room_id, room_number, room_type, bed_type,
             base_price, extra_charge, max_occupancy)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt =
                        conn.prepareStatement(sql)
        ) {

            pstmt.setString(
                    1,
                    room.getRoomId()
            );

            pstmt.setString(
                    2,
                    room.getRoomNumber()
            );

            // Determine room type
            if (room instanceof DeluxeRoom) {
                pstmt.setString(3, "Deluxe");
            } else if (room instanceof StandardRoom) {
                pstmt.setString(3, "Standard");
            } else {
                pstmt.setString(3, "HotelRoom");
            }

            pstmt.setString(
                    4,
                    room.getBedType()
            );

            pstmt.setDouble(
                    5,
                    room.getBasePrice()
            );

            pstmt.setDouble(
                    6,
                    room.getExtraCharge()
            );

            pstmt.setInt(
                    7,
                    room.getMaxOccupancy()
            );

            int rows =
                    pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // GET ALL ROOMS
    // =====================================================

    public List<HotelRoom> getAllRooms() {

        List<HotelRoom> rooms =
                new ArrayList<>();

        String sql =
                "SELECT * FROM rooms";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement pstmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        pstmt.executeQuery()
        ) {

            while (rs.next()) {

                rooms.add(
                        createRoomFromResultSet(rs)
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return rooms;
    }

    // =====================================================
    // GET ROOM BY ID
    // =====================================================

    public HotelRoom getRoomById(
            String roomId) {

        String sql =
                "SELECT * FROM rooms "
                + "WHERE room_id = ?";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement pstmt =
                        conn.prepareStatement(sql)
        ) {

            pstmt.setString(
                    1,
                    roomId
            );

            try (ResultSet rs =
                    pstmt.executeQuery()) {

                if (rs.next()) {

                    return createRoomFromResultSet(rs);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    // =====================================================
    // UPDATE ROOM
    // =====================================================

    public boolean updateRoom(
            HotelRoom room) {

        String sql = """
            UPDATE rooms SET
            room_number = ?,
            room_type = ?,
            bed_type = ?,
            base_price = ?,
            extra_charge = ?,
            max_occupancy = ?
            WHERE room_id = ?
            """;

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement pstmt =
                        conn.prepareStatement(sql)
        ) {

            if (room instanceof DeluxeRoom) {
                pstmt.setString(2, "Deluxe");
            } else {
                pstmt.setString(2, "Standard");
            }

            pstmt.setString(
                    1,
                    room.getRoomNumber()
            );

            pstmt.setString(
                    3,
                    room.getBedType()
            );

            pstmt.setDouble(
                    4,
                    room.getBasePrice()
            );

            pstmt.setDouble(
                    5,
                    room.getExtraCharge()
            );

            pstmt.setInt(
                    6,
                    room.getMaxOccupancy()
            );

            pstmt.setString(
                    7,
                    room.getRoomId()
            );

            int rows =
                    pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // DELETE ROOM
    // =====================================================

    public boolean deleteRoom(
            String roomId) {

        String sql =
                "DELETE FROM rooms "
                + "WHERE room_id = ?";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement pstmt =
                        conn.prepareStatement(sql)
        ) {

            pstmt.setString(
                    1,
                    roomId
            );

            int rows =
                    pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // CREATE DOMAIN OBJECT
    // =====================================================

    private HotelRoom createRoomFromResultSet(
            ResultSet rs) throws SQLException {

        String roomId =
                rs.getString("room_id");

        String roomNumber =
                rs.getString("room_number");

        String roomType =
                rs.getString("room_type");

        String bedType =
                rs.getString("bed_type");

        double basePrice =
                rs.getDouble("base_price");

        int maxOccupancy =
                rs.getInt("max_occupancy");

        if (roomType.equalsIgnoreCase("Deluxe")) {

            return new DeluxeRoom(
                    roomId,
                    roomNumber,
                    basePrice,
                    bedType,
                    maxOccupancy
            );

        } else {

            return new StandardRoom(
                    roomId,
                    roomNumber,
                    basePrice,
                    bedType,
                    maxOccupancy
            );
        }
    }
}