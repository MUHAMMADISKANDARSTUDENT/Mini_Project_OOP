import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PaymentGUI extends JFrame {

    private JTextField paymentIdField, paymentDateField, amountField;
    private JComboBox<String> methodComboBox, statusComboBox;
    private JTable paymentTable;
    private DefaultTableModel tableModel;

    public PaymentGUI() {
        setTitle("Hotel Reservation System - Payment Management");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("PAYMENT MANAGEMENT (UML: Payment)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Payment Class Attributes"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // paymentId & paymentDate
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Payment ID (paymentId):"), gbc);
        paymentIdField = new JTextField(15);
        gbc.gridx = 1; formPanel.add(paymentIdField, gbc);

        gbc.gridx = 2; gbc.gridy = 0; formPanel.add(new JLabel("Date (paymentDate):"), gbc);
        paymentDateField = new JTextField(15);
        gbc.gridx = 3; formPanel.add(paymentDateField, gbc);

        // amount & method
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Amount (amount):"), gbc);
        amountField = new JTextField(15);
        gbc.gridx = 1; formPanel.add(amountField, gbc);

        gbc.gridx = 2; gbc.gridy = 1; formPanel.add(new JLabel("Method (method):"), gbc);
        methodComboBox = new JComboBox<>(new String[]{"Cash", "Credit Card", "Debit Card", "Online Banking", "E-Wallet"});
        gbc.gridx = 3; formPanel.add(methodComboBox, gbc);

        // status
        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Status (status):"), gbc);
        statusComboBox = new JComboBox<>(new String[]{"Success", "Pending", "Refunded", "Failed"});
        gbc.gridx = 1; formPanel.add(statusComboBox, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Save");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(addButton); buttonPanel.add(updateButton); buttonPanel.add(deleteButton); buttonPanel.add(clearButton);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4;
        formPanel.add(buttonPanel, gbc);

        // Combined Top Panel to prevent BorderLayout.NORTH overwrite
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Table Columns matching Payment UML Attributes
        String[] columns = {"paymentId", "paymentDate", "amount", "method", "status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        paymentTable = new JTable(tableModel);
        paymentTable.setRowHeight(25);
        paymentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(paymentTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Payment Entity Table"));
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

        paymentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && paymentTable.getSelectedRow() >= 0) {
                int r = paymentTable.getSelectedRow();
                paymentIdField.setText(tableModel.getValueAt(r, 0).toString());
                paymentDateField.setText(tableModel.getValueAt(r, 1).toString());
                amountField.setText(tableModel.getValueAt(r, 2).toString());
                methodComboBox.setSelectedItem(tableModel.getValueAt(r, 3));
                statusComboBox.setSelectedItem(tableModel.getValueAt(r, 4));
            }
        });

        add(mainPanel);
    }

    private void addRow() {
        tableModel.addRow(new Object[]{
            paymentIdField.getText().trim(), paymentDateField.getText().trim(),
            amountField.getText().trim(), methodComboBox.getSelectedItem(), statusComboBox.getSelectedItem()
        });
        clearForm();
    }

    private void updateRow() {
        int r = paymentTable.getSelectedRow();
        if (r >= 0) {
            tableModel.setValueAt(paymentIdField.getText().trim(), r, 0);
            tableModel.setValueAt(paymentDateField.getText().trim(), r, 1);
            tableModel.setValueAt(amountField.getText().trim(), r, 2);
            tableModel.setValueAt(methodComboBox.getSelectedItem(), r, 3);
            tableModel.setValueAt(statusComboBox.getSelectedItem(), r, 4);
            clearForm();
        }
    }

    private void deleteRow() { int r = paymentTable.getSelectedRow(); if (r >= 0) { tableModel.removeRow(r); clearForm(); } }

    private void clearForm() {
        paymentIdField.setText(""); paymentDateField.setText(""); amountField.setText("");
        paymentTable.clearSelection();
    }

    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new PaymentGUI().setVisible(true)); }
}

