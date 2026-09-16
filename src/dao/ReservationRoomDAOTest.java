package dao;

public class ReservationRoomDAOTest {

    public static void main(String[] args) {

        ReservationRoomDAO reservationRoomDAO =
                new ReservationRoomDAO();

        String reservationId = "RES002";
        String roomId = "R002";

        // CREATE
        System.out.println("=== ADD RESERVATION ROOM ===");

        boolean added = reservationRoomDAO.addReservationRoom(
                reservationId,
                roomId
        );

        if (added) {
            System.out.println("Reservation room added successfully!");
        } else {
            System.out.println("Failed to add reservation room.");
        }

        // READ
        System.out.println(
                "\n=== ROOMS FOR RESERVATION RES002 ==="
        );

        reservationRoomDAO.getRoomsByReservation(
                reservationId
        );

        // DELETE
        System.out.println(
                "\n=== DELETE RESERVATION ROOM ==="
        );

        boolean deleted =
                reservationRoomDAO.deleteReservationRoom(
                        reservationId,
                        roomId
                );

        if (deleted) {
            System.out.println(
                    "Reservation room deleted successfully!"
            );
        } else {
            System.out.println(
                    "Failed to delete reservation room."
            );
        }

        // READ AFTER DELETE
        System.out.println(
                "\n=== ROOMS AFTER DELETE ==="
        );

        reservationRoomDAO.getRoomsByReservation(
                reservationId
        );
    }
}