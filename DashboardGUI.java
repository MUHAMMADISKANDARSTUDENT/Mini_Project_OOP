import javax.swing.*;
import java.awt.*;

public class DashboardGUI extends JFrame {

    public DashboardGUI() {

        setTitle("Hotel Reservation System - Dashboard");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 50, 30, 50
                )
        );

        // =========================
        // HEADER
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "HOTEL RESERVATION SYSTEM",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Grand Paradise Hotel",
                        SwingConstants.CENTER
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        JPanel headerPanel =
                new JPanel(
                        new GridLayout(2, 1)
                );

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =========================
        // MENU BUTTONS
        // =========================

        JPanel menuPanel =
                new JPanel(
                        new GridLayout(
                                4, 1, 15, 15
                        )
                );

        JButton roomButton =
                new JButton(
                        "Room Management"
                );

        JButton reservationButton =
                new JButton(
                        "Make Reservation"
                );

        JButton customerButton =
                new JButton(
                        "Customer Management"
                );

        JButton exitButton =
                new JButton(
                        "Exit"
                );

        menuPanel.add(roomButton);
        menuPanel.add(reservationButton);
        menuPanel.add(customerButton);
        menuPanel.add(exitButton);

        mainPanel.add(
                menuPanel,
                BorderLayout.CENTER
        );

        // =========================
        // ROOM BUTTON
        // =========================

        roomButton.addActionListener(e -> {

            RoomGUI roomGUI =
                    new RoomGUI();

            roomGUI.setVisible(true);

            dispose();

        });

        // =========================
        // RESERVATION BUTTON
        // =========================

        reservationButton.addActionListener(e -> {

            ReservationGUI reservationGUI =
                    new ReservationGUI();

            reservationGUI.setVisible(true);

            dispose();

        });

        // =========================
        // CUSTOMER BUTTON
        // =========================

        customerButton.addActionListener(e -> {

            CustomerGUI customerGUI =
                    new CustomerGUI();

            customerGUI.setVisible(true);

            dispose();

        });

        // =========================
        // EXIT BUTTON
        // =========================

        exitButton.addActionListener(e -> {

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to exit?",
                            "Confirm Exit",
                            JOptionPane.YES_NO_OPTION
                    );

            if (
                    result ==
                    JOptionPane.YES_OPTION
            ) {

                System.exit(0);
            }
        });

        add(mainPanel);
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            DashboardGUI dashboardGUI =
                    new DashboardGUI();

            dashboardGUI.setVisible(true);

        });
    }
}