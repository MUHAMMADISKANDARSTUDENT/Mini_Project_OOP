package dao;

public class PaymentDAOTest {

    public static void main(String[] args) {

        PaymentDAO paymentDAO = new PaymentDAO();

        // CREATE
        System.out.println("=== ADD PAYMENT ===");

        boolean added = paymentDAO.addPayment(
                "P001",
                "RES001",
                "2026-09-14",
                480.00,
                "Credit Card",
                "Paid"
        );

        if (added) {
            System.out.println("Payment added successfully!");
        } else {
            System.out.println("Failed to add payment.");
        }

        // READ ALL
        System.out.println("\n=== ALL PAYMENTS ===");

        for (String[] payment : paymentDAO.getAllPayments()) {

            System.out.println(
                    "Payment ID: " + payment[0]
                    + " | Reservation ID: " + payment[1]
                    + " | Payment Date: " + payment[2]
                    + " | Amount: RM" + payment[3]
                    + " | Method: " + payment[4]
                    + " | Status: " + payment[5]
            );
        }

        // READ ONE
        System.out.println("\n=== FIND PAYMENT P001 ===");

        String[] found = paymentDAO.getPaymentById("P001");

        if (found != null) {

            System.out.println(
                    "Payment ID: " + found[0]
                    + " | Reservation ID: " + found[1]
                    + " | Payment Date: " + found[2]
                    + " | Amount: RM" + found[3]
                    + " | Method: " + found[4]
                    + " | Status: " + found[5]
            );

        } else {
            System.out.println("Payment not found.");
        }

        // UPDATE
        System.out.println("\n=== UPDATE PAYMENT ===");

        boolean updated = paymentDAO.updatePayment(
                "P001",
                "RES001",
                "2026-09-15",
                500.00,
                "Online Banking",
                "Paid"
        );

        if (updated) {
            System.out.println("Payment updated successfully!");
        } else {
            System.out.println("Failed to update payment.");
        }

        // READ AGAIN
        System.out.println("\n=== PAYMENTS AFTER UPDATE ===");

        for (String[] payment : paymentDAO.getAllPayments()) {

            System.out.println(
                    "Payment ID: " + payment[0]
                    + " | Reservation ID: " + payment[1]
                    + " | Payment Date: " + payment[2]
                    + " | Amount: RM" + payment[3]
                    + " | Method: " + payment[4]
                    + " | Status: " + payment[5]
            );
        }

        // DELETE
        System.out.println("\n=== DELETE PAYMENT ===");

        boolean deleted = paymentDAO.deletePayment("P001");

        if (deleted) {
            System.out.println("Payment deleted successfully!");
        } else {
            System.out.println("Failed to delete payment.");
        }

        // READ AFTER DELETE
        System.out.println("\n=== PAYMENTS AFTER DELETE ===");

        for (String[] payment : paymentDAO.getAllPayments()) {

            System.out.println(
                    "Payment ID: " + payment[0]
                    + " | Reservation ID: " + payment[1]
                    + " | Payment Date: " + payment[2]
                    + " | Amount: RM" + payment[3]
                    + " | Method: " + payment[4]
                    + " | Status: " + payment[5]
            );
        }
    }
}