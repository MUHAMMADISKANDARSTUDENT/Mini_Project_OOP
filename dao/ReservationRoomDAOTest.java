package dao;

public class ReservationRoomDAOTest {

    public static void main(String[] args) {

        ReservationRoomDAO dao = new ReservationRoomDAO();

        boolean result = dao.deleteReservationRoom(
                "RES001",
                "R001"
        );

        if (result) {
            System.out.println("Room unlinked from reservation successfully!");
        } else {
            System.out.println("Failed to unlink room.");
        }
    }
}