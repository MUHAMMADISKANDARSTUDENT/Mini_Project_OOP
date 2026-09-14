package dao;

public class ReservationDAOTest {

    public static void main(String[] args) {

        ReservationDAO reservationDAO = new ReservationDAO();

        boolean result = reservationDAO.deleteReservation("RES001");

        if (result) {
            System.out.println("Reservation deleted successfully!");
        } else {
            System.out.println("Failed to delete reservation.");
        }
    }
}