import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PaymentGUI extends JFrame {

    private JTextField paymentIdField;
    private JTextField reservationIdField;
    private JTextField paymentDateField;
    private JTextField amountField;

    private JComboBox<String> methodComboBox;
    private JComboBox<String> statusComboBox;

    private JTable paymentTable;
    private DefaultTableModel tableModel;

    private final PaymentDAO paymentDAO = new PaymentDAO();

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public PaymentGUI() {

        setTitle("Hotel Reservation System - Payment Management");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel = new JLabel(
                "PAYMENT MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        // =========================
        // FORM PANEL
        // =========================

        JPanel formPanel = new JPanel(
                new GridBagLayout()
        );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Payment Class Attributes"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(6, 8, 6, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // =========================
        // PAYMENT ID
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Payment ID (paymentId):"),
                gbc
        );

        paymentIdField =
                new JTextField(15);

        gbc.gridx = 1;

        formPanel.add(
                paymentIdField,
                gbc
        );

        // =========================
        // RESERVATION ID
        // =========================

        gbc.gridx = 2;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Reservation ID (reservationId):"),
                gbc
        );

        reservationIdField =
                new JTextField(15);

        gbc.gridx = 3;

        formPanel.add(
                reservationIdField,
                gbc
        );

        // =========================
        // PAYMENT DATE
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Date (paymentDate):"),
                gbc
        );

        paymentDateField =
                new JTextField(15);

        gbc.gridx = 1;

        formPanel.add(
                paymentDateField,
                gbc
        );

        // =========================
        // AMOUNT
        // =========================

        gbc.gridx = 2;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Amount (amount):"),
                gbc
        );

        amountField =
                new JTextField(15);

        gbc.gridx = 3;

        formPanel.add(
                amountField,
                gbc
        );

        // =========================
        // METHOD
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Method (method):"),
                gbc
        );

        methodComboBox =
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
                methodComboBox,
                gbc
        );

        // =========================
        // STATUS
        // =========================

        gbc.gridx = 2;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Status (status):"),
                gbc
        );

        statusComboBox =
                new JComboBox<>(
                        new String[]{
                                "Success",
                                "Pending",
                                "Refunded",
                                "Failed"
                        }
                );

        gbc.gridx = 3;

        formPanel.add(
                statusComboBox,
                gbc
        );

        // =========================
        // BUTTONS
        // =========================

        JPanel buttonPanel =
                new JPanel(new FlowLayout());

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
        gbc.gridy = 3;
        gbc.gridwidth = 4;

        formPanel.add(
                buttonPanel,
                gbc
        );

        // =========================
        // TOP PANEL
        // =========================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        topPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        topPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        // =========================
        // TABLE
        // =========================

        String[] columns = {
                "paymentId",
                "reservationId",
                "paymentDate",
                "amount",
                "method",
                "status"
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
                new JScrollPane(paymentTable);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Payment Entity Table"
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
                                FlowLayout.CENTER,
                                10,
                                5
                        )
                );

        JButton backButton =
                new JButton("← Back to Dashboard");

        backButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        backButton.setPreferredSize(
                new Dimension(
                        220,
                        38
                )
        );

        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // BUTTON LISTENERS
        // =========================

        addButton.addActionListener(
                e -> addPayment()
        );

        updateButton.addActionListener(
                e -> updatePayment()
        );

        deleteButton.addActionListener(
                e -> deletePayment()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        backButton.addActionListener(
                e -> backToDashboard()
        );

        // =========================
        // TABLE SELECTION
        // =========================

        paymentTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                paymentTable.getSelectedRow();

                        if (row != -1) {

                            loadSelectedPayment(row);
                        }
                    }
                });

        add(mainPanel);

        // =========================
        // LOAD DATABASE DATA
        // =========================

        loadPayments();
    }

    // =====================================================
    // LOAD PAYMENTS
    // =====================================================

    private void loadPayments() {

        tableModel.setRowCount(0);

        try {

            List<Payment> payments =
                    paymentDAO.getAllPayments();

            for (Payment payment : payments) {

                tableModel.addRow(
                        new Object[]{
                                payment.getPaymentId(),
                                payment.getReservationId(),
                                payment.getPaymentDate(),
                                String.format(
                                        "RM %.2f",
                                        payment.getAmount()
                                ),
                                payment.getMethod(),
                                payment.getStatus()
                        }
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load payments.\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }

    // =====================================================
    // VALIDATE PAYMENT DATA
    // =====================================================

    private boolean validatePaymentData() {

        String paymentId =
                paymentIdField.getText().trim();

        String reservationId =
                reservationIdField.getText().trim();

        String paymentDate =
                paymentDateField.getText().trim();

        String amountText =
                amountField.getText().trim();

        // Payment ID
        if (paymentId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Payment ID cannot be empty.",
                    "Invalid Payment ID",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        // Reservation ID
        if (reservationId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation ID cannot be empty.",
                    "Invalid Reservation ID",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        // Payment Date
        if (paymentDate.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Payment date cannot be empty.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        try {

            LocalDate.parse(
                    paymentDate,
                    DATE_FORMAT
            );

        } catch (DateTimeParseException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid date.\n"
                    + "Format: yyyy-MM-dd\n"
                    + "Example: 2026-09-15",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        // Amount
        if (amountText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Amount cannot be empty.",
                    "Invalid Amount",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        try {

            double amount =
                    Double.parseDouble(
                            amountText
                                    .replace("RM", "")
                                    .trim()
                    );

            if (amount <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Amount must be greater than RM 0.00.",
                        "Invalid Amount",
                        JOptionPane.ERROR_MESSAGE
                );

                return false;
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid amount.\n"
                    + "Example: 150.00",
                    "Invalid Amount",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    // =====================================================
    // CREATE PAYMENT OBJECT
    // =====================================================

    private Payment createPaymentFromForm() {

        double amount =
                Double.parseDouble(
                        amountField
                                .getText()
                                .trim()
                                .replace("RM", "")
                                .trim()
                );

        return new Payment(

                paymentIdField
                        .getText()
                        .trim(),

                reservationIdField
                        .getText()
                        .trim(),

                paymentDateField
                        .getText()
                        .trim(),

                amount,

                methodComboBox
                        .getSelectedItem()
                        .toString(),

                statusComboBox
                        .getSelectedItem()
                        .toString()
        );
    }

    // =====================================================
    // ADD PAYMENT
    // =====================================================

    private void addPayment() {

        if (!validatePaymentData()) {
            return;
        }

        try {

            Payment payment =
                    createPaymentFromForm();

            boolean success =
                    paymentDAO.addPayment(payment);

            if (success) {

                loadPayments();
                clearForm();

                JOptionPane.showMessageDialog(
                        this,
                        "Payment saved successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment could not be saved.",
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }

    // =====================================================
    // UPDATE PAYMENT
    // =====================================================

    private void updatePayment() {

        int row =
                paymentTable.getSelectedRow();

        if (row < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a payment to update.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!validatePaymentData()) {
            return;
        }

        try {

            Payment payment =
                    createPaymentFromForm();

            boolean success =
                    paymentDAO.updatePayment(payment);

            if (success) {

                loadPayments();
                clearForm();

                JOptionPane.showMessageDialog(
                        this,
                        "Payment updated successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment could not be updated.",
                        "Update Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }

    // =====================================================
    // DELETE PAYMENT
    // =====================================================

    private void deletePayment() {

        int row =
                paymentTable.getSelectedRow();

        if (row < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a payment to delete.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String paymentId =
                tableModel
                        .getValueAt(row, 0)
                        .toString();

        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete payment "
                        + paymentId
                        + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirmation !=
                JOptionPane.YES_OPTION) {

            return;
        }

        try {

            boolean success =
                    paymentDAO.deletePayment(
                            paymentId
                    );

            if (success) {

                loadPayments();
                clearForm();

                JOptionPane.showMessageDialog(
                        this,
                        "Payment deleted successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment could not be deleted.",
                        "Delete Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }

    // =====================================================
    // LOAD SELECTED PAYMENT
    // =====================================================

    private void loadSelectedPayment(int row) {

        paymentIdField.setText(
                tableModel
                        .getValueAt(row, 0)
                        .toString()
        );

        reservationIdField.setText(
                tableModel
                        .getValueAt(row, 1)
                        .toString()
        );

        paymentDateField.setText(
                tableModel
                        .getValueAt(row, 2)
                        .toString()
        );

        amountField.setText(
                tableModel
                        .getValueAt(row, 3)
                        .toString()
                        .replace("RM", "")
                        .trim()
        );

        methodComboBox.setSelectedItem(
                tableModel
                        .getValueAt(row, 4)
        );

        statusComboBox.setSelectedItem(
                tableModel
                        .getValueAt(row, 5)
        );
    }

    // =====================================================
    // CLEAR FORM
    // =====================================================

    private void clearForm() {

        paymentIdField.setText("");
        reservationIdField.setText("");
        paymentDateField.setText("");
        amountField.setText("");

        methodComboBox.setSelectedIndex(0);
        statusComboBox.setSelectedIndex(0);

        paymentTable.clearSelection();
    }

    // =====================================================
    // BACK TO DASHBOARD
    // =====================================================

    private void backToDashboard() {

        dispose();

        SwingUtilities.invokeLater(() -> {

            DashboardGUI dashboard =
                    new DashboardGUI();

            dashboard.setLocationRelativeTo(null);
            dashboard.setVisible(true);
        });
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            PaymentGUI gui =
                    new PaymentGUI();

            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
        });
    }
}