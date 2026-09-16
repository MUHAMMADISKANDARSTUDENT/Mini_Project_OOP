import javax.swing.*;
import java.awt.*;

public class DashboardGUI extends JFrame {

    public DashboardGUI() {
        setTitle("Hotel Reservation System - Dashboard");
        setSize(600, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Header
        JLabel titleLabel = new JLabel("HOTEL RESERVATION DASHBOARD", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Navigation Buttons
        JPanel menuPanel = new JPanel(new GridLayout(5, 1, 12, 12));

        JButton userButton = new JButton("Customer Management");
        JButton roomButton = new JButton("Room Management");
        JButton reservationButton = new JButton("Reservation Management");
        JButton paymentButton = new JButton("Payment Management");
        JButton exitButton = new JButton("Exit System");

        menuPanel.add(userButton);
        menuPanel.add(roomButton);
        menuPanel.add(reservationButton);
        menuPanel.add(paymentButton);
        menuPanel.add(exitButton);

        mainPanel.add(menuPanel, BorderLayout.CENTER);

        // Navigation Actions
        userButton.addActionListener(e -> { new CustomerGUI().setVisible(true); dispose(); });
        roomButton.addActionListener(e -> { new RoomGUI().setVisible(true); dispose(); });
        reservationButton.addActionListener(e -> { new ReservationGUI().setVisible(true); dispose(); });
        paymentButton.addActionListener(e -> { new PaymentGUI().setVisible(true); dispose(); });

        exitButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardGUI().setVisible(true));
    }
}
