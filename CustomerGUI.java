import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerGUI extends JFrame {

    private JTextField customerIDField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;

    private JTable customerTable;
    private DefaultTableModel tableModel;

    public CustomerGUI() {

        setTitle("Hotel Reservation System - Customer Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 20, 30
                )
        );

        JLabel titleLabel = new JLabel(
                "CUSTOMER MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        JLabel subtitleLabel = new JLabel(
                "Grand Paradise Hotel",
                SwingConstants.CENTER
        );

        subtitleLabel.setFont(
                new Font("Arial", Font.PLAIN, 16)
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

        JPanel formPanel = new JPanel(
                new GridBagLayout()
        );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Customer Information"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 8, 8, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Customer ID:"),
                gbc
        );

        customerIDField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                customerIDField,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Customer Name:"),
                gbc
        );

        nameField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                nameField,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Phone Number:"),
                gbc
        );

        phoneField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                phoneField,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(
                new JLabel("Email:"),
                gbc
        );

        emailField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                emailField,
                gbc
        );

        JPanel buttonPanel =
                new JPanel();

        JButton addButton =
                new JButton("Save");

        JButton updateButton =
                new JButton("Update");

        JButton deleteButton =
                new JButton("Delete");

        JButton clearButton =
                new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        formPanel.add(
                buttonPanel,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        String[] columns = {
                "Customer ID",
                "Customer Name",
                "Phone Number",
                "Email"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        customerTable =
                new JTable(tableModel);

        customerTable.setRowHeight(25);

        customerTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        customerTable
                );

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Customer Records"
                )
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        JButton backButton =
                new JButton(
                        "Back to Dashboard"
                );

        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        addButton.addActionListener(e -> {
            addCustomer();
        });

        updateButton.addActionListener(e -> {
            updateCustomer();
        });

        deleteButton.addActionListener(e -> {
            deleteCustomer();
        });

        clearButton.addActionListener(e -> {
            clearForm();
        });

        backButton.addActionListener(e -> {

            DashboardGUI dashboardGUI =
                    new DashboardGUI();

            dashboardGUI.setVisible(true);

            dispose();

        });

        customerTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                customerTable
                                        .getSelectedRow();

                        if (row >= 0) {

                            customerIDField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    0
                                            )
                                            .toString()
                            );

                            nameField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    1
                                            )
                                            .toString()
                            );

                            phoneField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    2
                                            )
                                            .toString()
                            );

                            emailField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    3
                                            )
                                            .toString()
                            );
                        }
                    }
                });

        add(mainPanel);
    }

    private boolean validateForm() {

        String id =
                customerIDField
                        .getText()
                        .trim();

        String name =
                nameField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        String email =
                emailField
                        .getText()
                        .trim();

        if (
                id.isEmpty()
                || name.isEmpty()
                || phone.isEmpty()
                || email.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (!phone.matches("\\d+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must contain numbers only.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        if (!email.contains("@")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    private void addCustomer() {

        if (!validateForm()) {
            return;
        }

        String id =
                customerIDField
                        .getText()
                        .trim();

        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String existingID =
                    tableModel
                            .getValueAt(i, 0)
                            .toString();

            if (existingID.equals(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer ID already exists.",
                        "Duplicate ID",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to add this customer?",
                        "Confirm Save",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                JOptionPane.YES_OPTION
        ) {

            tableModel.addRow(
                    new Object[]{
                            id,
                            nameField.getText().trim(),
                            phoneField.getText().trim(),
                            emailField.getText().trim()
                    }
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Customer added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();
        }
    }

    private void updateCustomer() {

        int row =
                customerTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer first.",
                    "No Record Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!validateForm()) {
            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to update this customer?",
                        "Confirm Update",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                JOptionPane.YES_OPTION
        ) {

            tableModel.setValueAt(
                    customerIDField.getText().trim(),
                    row,
                    0
            );

            tableModel.setValueAt(
                    nameField.getText().trim(),
                    row,
                    1
            );

            tableModel.setValueAt(
                    phoneField.getText().trim(),
                    row,
                    2
            );

            tableModel.setValueAt(
                    emailField.getText().trim(),
                    row,
                    3
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Customer updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();
        }
    }

    private void deleteCustomer() {

        int row =
                customerTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer first.",
                    "No Record Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this customer?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                JOptionPane.YES_OPTION
        ) {

            tableModel.removeRow(row);

            JOptionPane.showMessageDialog(
                    this,
                    "Customer deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();
        }
    }

    private void clearForm() {

        customerIDField.setText("");

        nameField.setText("");

        phoneField.setText("");

        emailField.setText("");

        customerTable.clearSelection();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            CustomerGUI customerGUI =
                    new CustomerGUI();

            customerGUI.setVisible(true);

        });
    }
}