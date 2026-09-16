package dao;

public class ReservationDAOTest {

    public static void main(String[] args) {

        ReservationDAO reservationDAO = new ReservationDAO();

        String reservationId = "TEST_RES001";
        String userId = "U002";

        System.out.println("=== ADD RESERVATION ===");

        boolean added = reservationDAO.addReservation(
                reservationId,
                userId,
                "2026-09-16",
                "2026-10-01",
                "2026-10-03",
                "Pending",
                300.00
        );

        if (added) {
            System.out.println("Reservation added successfully!");
        } else {
            System.out.println("Failed to add reservation.");
        }

        System.out.println("\n=== ALL RESERVATIONS ===");

        for (String[] reservation : reservationDAO.getAllReservations()) {

            System.out.println(
                    "Reservation ID: " + reservation[0]
                    + " | User ID: " + reservation[1]
                    + " | Reservation Date: " + reservation[2]
                    + " | Check-in: " + reservation[3]
                    + " | Check-out: " + reservation[4]
                    + " | Status: " + reservation[5]
                    + " | Total: RM" + reservation[6]
            );
        }

        System.out.println("\n=== FIND RESERVATION TEST_RES001 ===");

        String[] found =
                reservationDAO.getReservationById(reservationId);

        if (found != null) {

            System.out.println(
                    "Reservation ID: " + found[0]
                    + " | User ID: " + found[1]
                    + " | Reservation Date: " + found[2]
                    + " | Check-in: " + found[3]
                    + " | Check-out: " + found[4]
                    + " | Status: " + found[5]
                    + " | Total: RM" + found[6]
            );

        } else {

            System.out.println("Reservation not found.");
        }

        System.out.println("\n=== UPDATE RESERVATION ===");

        boolean updated = reservationDAO.updateReservation(
                reservationId,
                userId,
                "2026-09-16",
                "2026-10-02",
                "2026-10-04",
                "Confirmed",
                400.00
        );

        if (updated) {
            System.out.println("Reservation updated successfully!");
        } else {
            System.out.println("Failed to update reservation.");
        }

        System.out.println("\n=== RESERVATION AFTER UPDATE ===");

        String[] updatedReservation =
                reservationDAO.getReservationById(reservationId);

        if (updatedReservation != null) {

            System.out.println(
                    "Reservation ID: " + updatedReservation[0]
                    + " | User ID: " + updatedReservation[1]
                    + " | Reservation Date: " + updatedReservation[2]
                    + " | Check-in: " + updatedReservation[3]
                    + " | Check-out: " + updatedReservation[4]
                    + " | Status: " + updatedReservation[5]
                    + " | Total: RM" + updatedReservation[6]
            );
        }

        System.out.println("\n=== DELETE RESERVATION ===");

        boolean deleted =
                reservationDAO.deleteReservation(reservationId);

        if (deleted) {
            System.out.println("Reservation deleted successfully!");
        } else {
            System.out.println("Failed to delete reservation.");
        }

        System.out.println("\n=== FIND RESERVATION AFTER DELETE ===");

        String[] deletedReservation =
                reservationDAO.getReservationById(reservationId);

        if (deletedReservation == null) {
            System.out.println(
                    "Reservation successfully deleted. Record not found."
            );
        } else {
            System.out.println("Reservation still exists.");
        }
    }
}