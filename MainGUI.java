import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public MainGUI() {

        setTitle("Hotel Reservation System");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 50, 30, 50)
        );

        // Header
        JLabel titleLabel = new JLabel(
                "HOTEL RESERVATION SYSTEM",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        JLabel subtitleLabel = new JLabel(
                "Grand Paradise Hotel",
                SwingConstants.CENTER
        );

        subtitleLabel.setFont(
                new Font("Arial", Font.PLAIN, 18)
        );

        JPanel headerPanel = new JPanel(
                new GridLayout(2, 1)
        );

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // Login Panel
        JPanel loginPanel = new JPanel(
                new GridBagLayout()
        );

        loginPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "System Login"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;

        loginPanel.add(
                new JLabel("Username:"),
                gbc
        );

        usernameField =
                new JTextField(20);

        gbc.gridx = 1;

        loginPanel.add(
                usernameField,
                gbc
        );

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;

        loginPanel.add(
                new JLabel("Password:"),
                gbc
        );

        passwordField =
                new JPasswordField(20);

        gbc.gridx = 1;

        loginPanel.add(
                passwordField,
                gbc
        );

        // Buttons
        JButton loginButton =
                new JButton("LOGIN");

        JButton exitButton =
                new JButton("EXIT");

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        loginPanel.add(
                buttonPanel,
                gbc
        );

        mainPanel.add(
                loginPanel,
                BorderLayout.CENTER
        );

        // Footer
        JLabel footerLabel = new JLabel(
                "Please login to continue",
                SwingConstants.CENTER
        );

        footerLabel.setFont(
                new Font("Arial", Font.ITALIC, 13)
        );

        mainPanel.add(
                footerLabel,
                BorderLayout.SOUTH
        );

        // Button Actions
        loginButton.addActionListener(
                e -> login()
        );

        exitButton.addActionListener(
                e -> exitSystem()
        );

        add(mainPanel);
    }

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (
                username.equals("admin")
                && password.equals("1234")
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            DashboardGUI dashboardGUI =
                    new DashboardGUI();

            dashboardGUI.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void exitSystem() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to exit?",
                        "Exit System",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result == JOptionPane.YES_OPTION
        ) {

            System.exit(0);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MainGUI mainGUI =
                    new MainGUI();

            mainGUI.setVisible(true);

        });
    }
}