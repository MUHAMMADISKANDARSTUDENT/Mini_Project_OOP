package dao;

public class ReservationDAOTest {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();
        ReservationDAO reservationDAO = new ReservationDAO();

        // Create a user first for the foreign key
        System.out.println("=== ADD USER FOR RESERVATION ===");
        userDAO.addUser(
                "U001",
                "Reservation Test User",
                "reservation@gmail.com",
                "0123456789"
        );

        // CREATE RESERVATION
        System.out.println("\n=== ADD RESERVATION ===");
        reservationDAO.addReservation(
                "RES001",
                "U001",
                "2026-09-14",
                "2026-09-20",
                "2026-09-22",
                "Confirmed",
                360.00
        );

        // READ
        System.out.println("\n=== ALL RESERVATIONS ===");
        reservationDAO.getAllReservations();

        // UPDATE
        System.out.println("\n=== UPDATE RESERVATION ===");
        reservationDAO.updateReservation(
                "RES001",
                "U001",
                "2026-09-14",
                "2026-09-21",
                "2026-09-23",
                "Confirmed",
                400.00
        );

        // READ AGAIN
        System.out.println("\n=== RESERVATIONS AFTER UPDATE ===");
        reservationDAO.getAllReservations();

        // DELETE
        System.out.println("\n=== DELETE RESERVATION ===");
        boolean result = reservationDAO.deleteReservation("RES001");

        if (result) {
            System.out.println("Reservation deleted successfully!");
        } else {
            System.out.println("Failed to delete reservation.");
        }

        // READ AGAIN
        System.out.println("\n=== RESERVATIONS AFTER DELETE ===");
        reservationDAO.getAllReservations();

        // Delete the test user
        System.out.println("\n=== DELETE TEST USER ===");
        userDAO.deleteUser("U001");
    }
}