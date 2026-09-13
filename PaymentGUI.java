import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PaymentGUI extends JFrame {

    private JTextField paymentIDField;
    private JTextField reservationIDField;
    private JTextField amountField;
    private JTextField dateField;

    private JComboBox<String> paymentMethodComboBox;
    private JComboBox<String> paymentStatusComboBox;

    private JTable paymentTable;
    private DefaultTableModel tableModel;

    public PaymentGUI() {

        setTitle("Hotel Reservation System - Payment");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 20, 30
                )
        );

        // =========================
        // HEADER
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "PAYMENT MANAGEMENT",
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
        // FORM
        // =========================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Payment Information"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(6, 8, 6, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // Payment ID
        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Payment ID:"),
                gbc
        );

        paymentIDField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                paymentIDField,
                gbc
        );

        // Reservation ID
        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Reservation ID:"),
                gbc
        );

        reservationIDField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                reservationIDField,
                gbc
        );

        // Amount
        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Amount (RM):"),
                gbc
        );

        amountField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                amountField,
                gbc
        );

        // Payment Date
        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(
                new JLabel("Payment Date:"),
                gbc
        );

        dateField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                dateField,
                gbc
        );

        // Payment Method
        gbc.gridx = 0;
        gbc.gridy = 4;

        formPanel.add(
                new JLabel("Payment Method:"),
                gbc
        );

        paymentMethodComboBox =
                new JComboBox<>(
                        new String[]{
                                "Cash",
                                "Credit Card",
                                "Debit Card",
                                "Online Banking",
                                "E-Wallet"
                        }
                );

        gbc.gridx = 1;

        formPanel.add(
                paymentMethodComboBox,
                gbc
        );

        // Payment Status
        gbc.gridx = 0;
        gbc.gridy = 5;

        formPanel.add(
                new JLabel("Payment Status:"),
                gbc
        );

        paymentStatusComboBox =
                new JComboBox<>(
                        new String[]{
                                "Paid",
                                "Pending",
                                "Refunded"
                        }
                );

        gbc.gridx = 1;

        formPanel.add(
                paymentStatusComboBox,
                gbc
        );

        // =========================
        // BUTTONS
        // =========================

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
        gbc.gridy = 6;
        gbc.gridwidth = 2;

        formPanel.add(
                buttonPanel,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        // =========================
        // TABLE
        // =========================

        String[] columns = {
                "Payment ID",
                "Reservation ID",
                "Amount (RM)",
                "Date",
                "Payment Method",
                "Status"
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

        paymentTable =
                new JTable(tableModel);

        paymentTable.setRowHeight(25);

        paymentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        paymentTable
                );

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Payment Records"
                )
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =========================
        // BACK BUTTON
        // =========================

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

        bottomPanel.add(
                backButton
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // EVENT HANDLING
        // =========================

        addButton.addActionListener(e -> {
            addPayment();
        });

        updateButton.addActionListener(e -> {
            updatePayment();
        });

        deleteButton.addActionListener(e -> {
            deletePayment();
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

        // =========================
        // TABLE SELECTION
        // =========================

        paymentTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                paymentTable
                                        .getSelectedRow();

                        if (row >= 0) {

                            paymentIDField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    0
                                            )
                                            .toString()
                            );

                            reservationIDField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    1
                                            )
                                            .toString()
                            );

                            amountField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    2
                                            )
                                            .toString()
                            );

                            dateField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    3
                                            )
                                            .toString()
                            );

                            paymentMethodComboBox
                                    .setSelectedItem(
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            4
                                                    )
                                    );

                            paymentStatusComboBox
                                    .setSelectedItem(
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            5
                                                    )
                                    );
                        }
                    }
                });

        add(mainPanel);
    }

    // =========================
    // VALIDATION
    // =========================

    private boolean validateForm() {

        String paymentID =
                paymentIDField
                        .getText()
                        .trim();

        String reservationID =
                reservationIDField
                        .getText()
                        .trim();

        String amount =
                amountField
                        .getText()
                        .trim();

        String date =
                dateField
                        .getText()
                        .trim();

        if (
                paymentID.isEmpty()
                || reservationID.isEmpty()
                || amount.isEmpty()
                || date.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all required fields.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        try {

            double paymentAmount =
                    Double.parseDouble(amount);

            if (paymentAmount <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment amount must be greater than 0.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return false;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Amount must be a valid number.",
                    "Invalid Amount",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    // =========================
    // ADD PAYMENT
    // =========================

    private void addPayment() {

        if (!validateForm()) {
            return;
        }

        String paymentID =
                paymentIDField
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

            if (
                    existingID.equals(paymentID)
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment ID already exists.",
                        "Duplicate ID",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to save this payment?",
                        "Confirm Payment",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                JOptionPane.YES_OPTION
        ) {

            tableModel.addRow(
                    new Object[]{
                            paymentID,
                            reservationIDField
                                    .getText()
                                    .trim(),
                            amountField
                                    .getText()
                                    .trim(),
                            dateField
                                    .getText()
                                    .trim(),
                            paymentMethodComboBox
                                    .getSelectedItem(),
                            paymentStatusComboBox
                                    .getSelectedItem()
                    }
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Payment added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();
        }
    }

    // =========================
    // UPDATE PAYMENT
    // =========================

    private void updatePayment() {

        int row =
                paymentTable.getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a payment record first.",
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
                        "Are you sure you want to update this payment?",
                        "Confirm Update",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                JOptionPane.YES_OPTION
        ) {

            tableModel.setValueAt(
                    paymentIDField
                            .getText()
                            .trim(),
                    row,
                    0
            );

            tableModel.setValueAt(
                    reservationIDField
                            .getText()
                            .trim(),
                    row,
                    1
            );

            tableModel.setValueAt(
                    amountField
                            .getText()
                            .trim(),
                    row,
                    2
            );

            tableModel.setValueAt(
                    dateField
                            .getText()
                            .trim(),
                    row,
                    3
            );

            tableModel.setValueAt(
                    paymentMethodComboBox
                            .getSelectedItem(),
                    row,
                    4
            );

            tableModel.setValueAt(
                    paymentStatusComboBox
                            .getSelectedItem(),
                    row,
                    5
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Payment updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();
        }
    }

    // =========================
    // DELETE PAYMENT
    // =========================

    private void deletePayment() {

        int row =
                paymentTable.getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a payment record first.",
                    "No Record Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this payment?",
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
                    "Payment deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();
        }
    }

    // =========================
    // CLEAR FORM
    // =========================

    private void clearForm() {

        paymentIDField.setText("");

        reservationIDField.setText("");

        amountField.setText("");

        dateField.setText("");

        paymentMethodComboBox
                .setSelectedIndex(0);

        paymentStatusComboBox
                .setSelectedIndex(0);

        paymentTable.clearSelection();
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            PaymentGUI paymentGUI =
                    new PaymentGUI();

            paymentGUI.setVisible(true);

        });
    }
}