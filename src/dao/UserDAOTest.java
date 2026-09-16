package dao;

public class UserDAOTest {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        System.out.println("=== ADD USER ===");

        boolean added = userDAO.addUser(
                "TEST001",
                "Test User",
                "test@gmail.com",
                "0123456789"
        );

        if (added) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user.");
        }

        System.out.println("\n=== ALL USERS ===");

        for (String[] user : userDAO.getAllUsers()) {

            System.out.println(
                    "User ID: " + user[0]
                    + " | Name: " + user[1]
                    + " | Email: " + user[2]
                    + " | Phone: " + user[3]
            );
        }

        System.out.println("\n=== FIND USER TEST001 ===");

        String[] found = userDAO.getUserById("TEST001");

        if (found != null) {

            System.out.println(
                    "User ID: " + found[0]
                    + " | Name: " + found[1]
                    + " | Email: " + found[2]
                    + " | Phone: " + found[3]
            );

        } else {

            System.out.println("User not found.");
        }

        System.out.println("\n=== UPDATE USER ===");

        boolean updated = userDAO.updateUser(
                "TEST001",
                "Updated Test User",
                "updatedtest@gmail.com",
                "01122334455"
        );

        if (updated) {
            System.out.println("User updated successfully!");
        } else {
            System.out.println("Failed to update user.");
        }

        System.out.println("\n=== USERS AFTER UPDATE ===");

        for (String[] user : userDAO.getAllUsers()) {

            System.out.println(
                    "User ID: " + user[0]
                    + " | Name: " + user[1]
                    + " | Email: " + user[2]
                    + " | Phone: " + user[3]
            );
        }

        System.out.println("\n=== DELETE USER ===");

        boolean deleted = userDAO.deleteUser("TEST001");

        if (deleted) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("Failed to delete user.");
        }

        System.out.println("\n=== USERS AFTER DELETE ===");

        for (String[] user : userDAO.getAllUsers()) {

            System.out.println(
                    "User ID: " + user[0]
                    + " | Name: " + user[1]
                    + " | Email: " + user[2]
                    + " | Phone: " + user[3]
            );
        }
    }
}