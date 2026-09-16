import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MainGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    // Store usernames and passwords
    private HashMap<String, String> accounts;

    public MainGUI() {
        setTitle("Hotel Reservation System - Login");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create login accounts
        accounts = new HashMap<>();
        accounts.put("admin", "1234");
        accounts.put("staff1", "1111");
        accounts.put("staff2", "2222");
        accounts.put("manager", "3333");

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Header
        JLabel titleLabel = new JLabel("HOTEL RESERVATION SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel subtitleLabel = new JLabel("System Login", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Login Panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createTitledBorder("Authentication"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(18);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(18);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        // Buttons
        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loginPanel.add(buttonPanel, gbc);

        mainPanel.add(loginPanel, BorderLayout.CENTER);

        // Button Listeners
        loginButton.addActionListener(e -> login());
        exitButton.addActionListener(e -> System.exit(0));

        add(mainPanel);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Check whether username exists and password matches
        if (accounts.containsKey(username) &&
            accounts.get(username).equals(password)) {

            JOptionPane.showMessageDialog(
                this,
                "Login successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );

            new DashboardGUI().setVisible(true);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}
