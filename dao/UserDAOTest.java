package dao;

public class UserDAOTest {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        // CREATE
        System.out.println("=== ADD USER ===");
        userDAO.addUser(
                "U001",
                "Test User",
                "test@gmail.com",
                "0123456789"
        );

        // READ
        System.out.println("\n=== ALL USERS ===");
        userDAO.getAllUsers();

        // UPDATE
        System.out.println("\n=== UPDATE USER ===");
        userDAO.updateUser(
                "U001",
                "Updated User",
                "updated@gmail.com",
                "0198765432"
        );

        // READ AGAIN
        System.out.println("\n=== USERS AFTER UPDATE ===");
        userDAO.getAllUsers();

        // DELETE
        System.out.println("\n=== DELETE USER ===");
        boolean result = userDAO.deleteUser("U001");

        if (result) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("Failed to delete user.");
        }

        // READ AGAIN
        System.out.println("\n=== USERS AFTER DELETE ===");
        userDAO.getAllUsers();
    }
}