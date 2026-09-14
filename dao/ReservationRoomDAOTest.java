package dao;

public class ReservationRoomDAOTest {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();
        RoomDAO roomDAO = new RoomDAO();
        ReservationDAO reservationDAO = new ReservationDAO();
        ReservationRoomDAO reservationRoomDAO = new ReservationRoomDAO();

        // Create user
        System.out.println("=== ADD USER ===");
        userDAO.addUser(
                "U001",
                "Reservation Room Test User",
                "rrtest@gmail.com",
                "0123456789"
        );

        // Create room
        System.out.println("\n=== ADD ROOM ===");
        roomDAO.addRoom(
                "R001",
                "101",
                "Deluxe",
                150.00,
                "King",
                2,
                "Breakfast"
        );

        // Create reservation
        System.out.println("\n=== ADD RESERVATION ===");
        reservationDAO.addReservation(
                "RES001",
                "U001",
                "2026-09-14",
                "2026-09-20",
                "2026-09-22",
                "Confirmed",
                300.00
        );

        // CREATE relationship
        System.out.println("\n=== ADD RESERVATION ROOM ===");
        reservationRoomDAO.addReservationRoom("RES001", "R001");

        // READ relationship
        System.out.println("\n=== ROOMS FOR RESERVATION ===");
        reservationRoomDAO.getRoomsByReservation("RES001");

        // DELETE relationship
        System.out.println("\n=== DELETE RESERVATION ROOM ===");
        boolean result =
                reservationRoomDAO.deleteReservationRoom("RES001", "R001");

        if (result) {
            System.out.println("Reservation-room deleted successfully!");
        } else {
            System.out.println("Failed to delete reservation-room.");
        }

        // READ AGAIN
        System.out.println("\n=== ROOMS AFTER DELETE ===");
        reservationRoomDAO.getRoomsByReservation("RES001");

        // Clean up
        reservationDAO.deleteReservation("RES001");
        roomDAO.deleteRoom("R001");
        userDAO.deleteUser("U001");
    }
}