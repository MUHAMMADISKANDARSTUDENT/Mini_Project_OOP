package dao;

public class RoomDAOTest {

    public static void main(String[] args) {

        RoomDAO roomDAO = new RoomDAO();

        boolean result = roomDAO.deleteRoom("R001");

        if (result) {
            System.out.println("Room deleted successfully!");
        } else {
            System.out.println("Failed to delete room.");
        }
    }
}