package src.dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.model.Reservation;

public class ReservationDAO {

    // CREATE
    public boolean addReservation(Reservation reservation) {

        String sql = """
            INSERT INTO reservations
            (
                reservation_id,
                user_id,
                room_id,
                reservation_date,
                check_in_date,
                check_out_date,
                status,
                total_amount
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservation.getReservationId());
            stmt.setString(2, reservation.getUserId());
            stmt.setString(3, reservation.getRoomId());
            stmt.setString(4, reservation.getReservationDate());
            stmt.setString(5, reservation.getCheckInDate());
            stmt.setString(6, reservation.getCheckOutDate());
            stmt.setString(7, reservation.getStatus());
            stmt.setDouble(8, reservation.getTotalAmount());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // READ ALL
    public List<Reservation> getAllReservations() {

        List<Reservation> reservations = new ArrayList<>();

        String sql = """
            SELECT reservation_id, user_id, room_id,
                   reservation_date, check_in_date,
                   check_out_date, status, total_amount
            FROM reservations
            ORDER BY reservation_id
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Reservation reservation = new Reservation(
                    rs.getString("reservation_id"),
                    rs.getString("user_id"),
                    rs.getString("room_id"),
                    rs.getString("reservation_date"),
                    rs.getString("check_in_date"),
                    rs.getString("check_out_date"),
                    rs.getString("status"),
                    rs.getDouble("total_amount")
                );

                reservations.add(reservation);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reservations;
    }

    // READ ONE RESERVATION
    public Reservation getReservationById(String reservationId) {

        String sql = """
            SELECT reservation_id, user_id, room_id,
                   reservation_date, check_in_date,
                   check_out_date, status, total_amount
            FROM reservations
            WHERE reservation_id = ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return new Reservation(
                        rs.getString("reservation_id"),
                        rs.getString("user_id"),
                        rs.getString("room_id"),
                        rs.getString("reservation_date"),
                        rs.getString("check_in_date"),
                        rs.getString("check_out_date"),
                        rs.getString("status"),
                        rs.getDouble("total_amount")
                    );
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    // UPDATE
    public boolean updateReservation(Reservation reservation) {

        String sql = """
            UPDATE reservations
            SET user_id = ?,
                room_id = ?,
                reservation_date = ?,
                check_in_date = ?,
                check_out_date = ?,
                status = ?,
                total_amount = ?
            WHERE reservation_id = ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservation.getUserId());
            stmt.setString(2, reservation.getRoomId());
            stmt.setString(3, reservation.getReservationDate());
            stmt.setString(4, reservation.getCheckInDate());
            stmt.setString(5, reservation.getCheckOutDate());
            stmt.setString(6, reservation.getStatus());
            stmt.setDouble(7, reservation.getTotalAmount());
            stmt.setString(8, reservation.getReservationId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteReservation(String reservationId) {

        String sql = """
            DELETE FROM reservations
            WHERE reservation_id = ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reservationId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}