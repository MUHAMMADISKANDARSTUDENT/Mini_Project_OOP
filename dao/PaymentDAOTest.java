package dao;

public class PaymentDAOTest {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();
        ReservationDAO reservationDAO = new ReservationDAO();
        PaymentDAO paymentDAO = new PaymentDAO();

        // Create user for foreign key
        System.out.println("=== ADD USER ===");
        userDAO.addUser(
                "U001",
                "Payment Test User",
                "payment@gmail.com",
                "0123456789"
        );

        // Create reservation for foreign key
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

        // CREATE PAYMENT
        System.out.println("\n=== ADD PAYMENT ===");
        paymentDAO.addPayment(
                "P001",
                "RES001",
                "2026-09-14",
                360.00,
                "Credit Card",
                "Paid"
        );

        // READ
        System.out.println("\n=== ALL PAYMENTS ===");
        paymentDAO.getAllPayments();

        // UPDATE
        System.out.println("\n=== UPDATE PAYMENT ===");
        paymentDAO.updatePayment(
                "P001",
                "RES001",
                "2026-09-14",
                400.00,
                "Online Banking",
                "Paid"
        );

        // READ AGAIN
        System.out.println("\n=== PAYMENTS AFTER UPDATE ===");
        paymentDAO.getAllPayments();

        // DELETE
        System.out.println("\n=== DELETE PAYMENT ===");
        boolean result = paymentDAO.deletePayment("P001");

        if (result) {
            System.out.println("Payment deleted successfully!");
        } else {
            System.out.println("Failed to delete payment.");
        }

        // READ AGAIN
        System.out.println("\n=== PAYMENTS AFTER DELETE ===");
        paymentDAO.getAllPayments();

        // Clean up reservation and user
        reservationDAO.deleteReservation("RES001");
        userDAO.deleteUser("U001");
    }
}