import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RoomGUI extends JFrame {

    private JTextField userIdField, nameField, emailField, phoneField;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public RoomGUI() {
        setTitle("Hotel Reservation System - User Management");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("USER MANAGEMENT (UML: User)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("User Information"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // userId & name
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("User ID (userId):"), gbc);
        userIdField = new JTextField(15);
        gbc.gridx = 1; formPanel.add(userIdField, gbc);

        gbc.gridx = 2; gbc.gridy = 0; formPanel.add(new JLabel("Full Name (name):"), gbc);
        nameField = new JTextField(15);
        gbc.gridx = 3; formPanel.add(nameField, gbc);

        // email & phone
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Email Address (email):"), gbc);
        emailField = new JTextField(15);
        gbc.gridx = 1; formPanel.add(emailField, gbc);

        gbc.gridx = 2; gbc.gridy = 1; formPanel.add(new JLabel("Phone (phone):"), gbc);
        phoneField = new JTextField(15);
        gbc.gridx = 3; formPanel.add(phoneField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Save");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4;
        formPanel.add(buttonPanel, gbc);

        // Combined Top Panel to prevent BorderLayout.NORTH overwrite
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"userId", "name", "email", "phone"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        userTable = new JTable(tableModel);
        userTable.setRowHeight(25);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("User Records"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer Back Button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back to Dashboard");
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Listeners
        addButton.addActionListener(e -> addRow());
        updateButton.addActionListener(e -> updateRow());
        deleteButton.addActionListener(e -> deleteRow());
        clearButton.addActionListener(e -> clearForm());
        backButton.addActionListener(e -> { new DashboardGUI().setVisible(true); dispose(); });

        userTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && userTable.getSelectedRow() >= 0) {
                int r = userTable.getSelectedRow();
                userIdField.setText(tableModel.getValueAt(r, 0).toString());
                nameField.setText(tableModel.getValueAt(r, 1).toString());
                emailField.setText(tableModel.getValueAt(r, 2).toString());
                phoneField.setText(tableModel.getValueAt(r, 3).toString());
            }
        });

        add(mainPanel);
    }

    private void addRow() {
        if (userIdField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        tableModel.addRow(new Object[]{ userIdField.getText().trim(), nameField.getText().trim(), emailField.getText().trim(), phoneField.getText().trim() });
        clearForm();
    }

    private void updateRow() {
        int r = userTable.getSelectedRow();
        if (r >= 0) {
            tableModel.setValueAt(userIdField.getText().trim(), r, 0);
            tableModel.setValueAt(nameField.getText().trim(), r, 1);
            tableModel.setValueAt(emailField.getText().trim(), r, 2);
            tableModel.setValueAt(phoneField.getText().trim(), r, 3);
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Selection Required", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteRow() {
        int r = userTable.getSelectedRow();
        if (r >= 0) {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Delete Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                tableModel.removeRow(r);
                clearForm();
            }
        }
    }

    private void clearForm() {
        userIdField.setText(""); nameField.setText(""); emailField.setText(""); phoneField.setText("");
        userTable.clearSelection();
    }

    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new CustomerGUI().setVisible(true)); }
}
