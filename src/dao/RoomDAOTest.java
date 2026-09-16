package dao;

public class RoomDAOTest {

    public static void main(String[] args) {

        RoomDAO roomDAO = new RoomDAO();

        System.out.println("=== ADD ROOM ===");

        boolean added = roomDAO.addRoom(
                "TEST_R001",
                "999",
                "Test Room",
                "King",
                160.00,
                "Breakfast",
                2
        );

        if (added) {
            System.out.println("Room added successfully!");
        } else {
            System.out.println("Failed to add room.");
        }

        System.out.println("\n=== ALL ROOMS ===");

        for (String[] room : roomDAO.getAllRooms()) {

            System.out.println(
                    room[0] + " | "
                    + room[1] + " | "
                    + room[2] + " | "
                    + room[3] + " | RM"
                    + room[4] + " | "
                    + room[5] + " | "
                    + room[6]
            );
        }

        System.out.println("\n=== FIND ROOM TEST_R001 ===");

        String[] found = roomDAO.getRoomById("TEST_R001");

        if (found != null) {

            System.out.println(
                    "Room ID: " + found[0]
                    + " | Room Number: " + found[1]
                    + " | Room Type: " + found[2]
                    + " | Bed Type: " + found[3]
                    + " | Base Price: RM" + found[4]
                    + " | Extra Service: " + found[5]
                    + " | Max Occupancy: " + found[6]
            );

        } else {

            System.out.println("Room not found.");
        }

        System.out.println("\n=== UPDATE ROOM TEST_R001 ===");

        boolean updated = roomDAO.updateRoom(
                "TEST_R001",
                "999",
                "Updated Test Room",
                "Queen",
                180.00,
                "Breakfast + WiFi",
                2
        );

        if (updated) {
            System.out.println("Room updated successfully!");
        } else {
            System.out.println("Failed to update room.");
        }

        System.out.println("\n=== ROOMS AFTER UPDATE ===");

        for (String[] room : roomDAO.getAllRooms()) {

            if (room[0].equals("TEST_R001")) {

                System.out.println(
                        room[0] + " | "
                        + room[1] + " | "
                        + room[2] + " | "
                        + room[3] + " | RM"
                        + room[4] + " | "
                        + room[5] + " | "
                        + room[6]
                );
            }
        }

        System.out.println("\n=== DELETE ROOM TEST_R001 ===");

        boolean deleted = roomDAO.deleteRoom("TEST_R001");

        if (deleted) {
            System.out.println("Room deleted successfully!");
        } else {
            System.out.println("Failed to delete room.");
        }

        System.out.println("\n=== FIND ROOM AFTER DELETE ===");

        String[] deletedRoom = roomDAO.getRoomById("TEST_R001");

        if (deletedRoom == null) {
            System.out.println("Room successfully deleted. Record not found.");
        } else {
            System.out.println("Room still exists.");
        }
    }
}