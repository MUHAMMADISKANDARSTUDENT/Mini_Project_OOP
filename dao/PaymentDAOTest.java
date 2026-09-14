package dao;

public class PaymentDAOTest {

    public static void main(String[] args) {

        PaymentDAO paymentDAO = new PaymentDAO();

        boolean result = paymentDAO.deletePayment("P001");

        if (result) {
            System.out.println("Payment deleted successfully!");
        } else {
            System.out.println("Failed to delete payment.");
        }
    }
}