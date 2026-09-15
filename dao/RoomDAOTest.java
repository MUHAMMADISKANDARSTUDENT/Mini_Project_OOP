package dao;

public class RoomDAOTest {

    public static void main(String[] args) {

        RoomDAO roomDAO = new RoomDAO();

        // CREATE
        System.out.println("=== ADD ROOM ===");
        roomDAO.addRoom(
                "R001",
                "101",
                "Deluxe",
                150.00,
                "King",
                2,
                "Breakfast"
        );

        // READ
        System.out.println("\n=== ALL ROOMS ===");
        roomDAO.getAllRooms();

        // UPDATE
        System.out.println("\n=== UPDATE ROOM ===");
        roomDAO.updateRoom(
                "R001",
                "101",
                "Premium Deluxe",
                180.00,
                "King",
                2,
                "Breakfast + WiFi"
        );

        // READ AGAIN
        System.out.println("\n=== ROOMS AFTER UPDATE ===");
        roomDAO.getAllRooms();

        // DELETE
        System.out.println("\n=== DELETE ROOM ===");
        boolean result = roomDAO.deleteRoom("R001");

        if (result) {
            System.out.println("Room deleted successfully!");
        } else {
            System.out.println("Failed to delete room.");
        }

        // READ AGAIN
        System.out.println("\n=== ROOMS AFTER DELETE ===");
        roomDAO.getAllRooms();
    }
}