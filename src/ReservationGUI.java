import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReservationGUI extends JFrame {

    private JTextField reservationIdField, reservationDateField, checkInDateField, checkOutDateField, totalAmountField, roomsField;
    private JComboBox<String> statusComboBox;
    private JTable reservationTable;
    private DefaultTableModel tableModel;

    public ReservationGUI() {
        setTitle("Hotel Reservation System - Reservation Management");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("RESERVATION MANAGEMENT (UML: Reservation)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Reservation Class Attributes"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 6, 5, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // reservationId & reservationDate
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Reservation ID:"), gbc);
        reservationIdField = new JTextField(12);
        gbc.gridx = 1; formPanel.add(reservationIdField, gbc);

        gbc.gridx = 2; gbc.gridy = 0; formPanel.add(new JLabel("Res. Date (YYYY-MM-DD):"), gbc);
        reservationDateField = new JTextField(12);
        gbc.gridx = 3; formPanel.add(reservationDateField, gbc);

        // checkInDate & checkOutDate
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Check In (checkInDate):"), gbc);
        checkInDateField = new JTextField(12);
        gbc.gridx = 1; formPanel.add(checkInDateField, gbc);

        gbc.gridx = 2; gbc.gridy = 1; formPanel.add(new JLabel("Check Out (checkOutDate):"), gbc);
        checkOutDateField = new JTextField(12);
        gbc.gridx = 3; formPanel.add(checkOutDateField, gbc);

        // status & totalAmount
        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Status (status):"), gbc);
        statusComboBox = new JComboBox<>(new String[]{"Pending", "Confirmed", "Cancelled"});
        gbc.gridx = 1; formPanel.add(statusComboBox, gbc);

        gbc.gridx = 2; gbc.gridy = 2; formPanel.add(new JLabel("Total Amount ($):"), gbc);
        totalAmountField = new JTextField(12);
        gbc.gridx = 3; formPanel.add(totalAmountField, gbc);

        // rooms field
        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(new JLabel("Rooms (rooms):"), gbc);
        roomsField = new JTextField(12);
        gbc.gridx = 1; gbc.gridwidth = 3; formPanel.add(roomsField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Save");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(addButton); buttonPanel.add(updateButton); buttonPanel.add(deleteButton); buttonPanel.add(clearButton);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4;
        formPanel.add(buttonPanel, gbc);

        // Combined Top Panel to prevent BorderLayout.NORTH overwrite
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Table Columns matching Reservation UML Class Attributes
        String[] columns = {"reservationId", "reservationDate", "checkInDate", "checkOutDate", "status", "totalAmount", "rooms"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        reservationTable = new JTable(tableModel);
        reservationTable.setRowHeight(25);
        reservationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(reservationTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Reservation Entity Table"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer Back Button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back to Dashboard");
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        addButton.addActionListener(e -> addRow());
        updateButton.addActionListener(e -> updateRow());
        deleteButton.addActionListener(e -> deleteRow());
        clearButton.addActionListener(e -> clearForm());
        backButton.addActionListener(e -> { new DashboardGUI().setVisible(true); dispose(); });

        reservationTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && reservationTable.getSelectedRow() >= 0) {
                int r = reservationTable.getSelectedRow();
                reservationIdField.setText(tableModel.getValueAt(r, 0).toString());
                reservationDateField.setText(tableModel.getValueAt(r, 1).toString());
                checkInDateField.setText(tableModel.getValueAt(r, 2).toString());
                checkOutDateField.setText(tableModel.getValueAt(r, 3).toString());
                statusComboBox.setSelectedItem(tableModel.getValueAt(r, 4));
                totalAmountField.setText(tableModel.getValueAt(r, 5).toString());
                roomsField.setText(tableModel.getValueAt(r, 6).toString());
            }
        });

        add(mainPanel);
    }

    private void addRow() {
        tableModel.addRow(new Object[]{
            reservationIdField.getText().trim(), reservationDateField.getText().trim(),
            checkInDateField.getText().trim(), checkOutDateField.getText().trim(),
            statusComboBox.getSelectedItem(), totalAmountField.getText().trim(), roomsField.getText().trim()
        });
        clearForm();
    }

    private void updateRow() {
        int r = reservationTable.getSelectedRow();
        if (r >= 0) {
            tableModel.setValueAt(reservationIdField.getText().trim(), r, 0);
            tableModel.setValueAt(reservationDateField.getText().trim(), r, 1);
            tableModel.setValueAt(checkInDateField.getText().trim(), r, 2);
            tableModel.setValueAt(checkOutDateField.getText().trim(), r, 3);
            tableModel.setValueAt(statusComboBox.getSelectedItem(), r, 4);
            tableModel.setValueAt(totalAmountField.getText().trim(), r, 5);
            tableModel.setValueAt(roomsField.getText().trim(), r, 6);
            clearForm();
        }
    }

    private void deleteRow() { int r = reservationTable.getSelectedRow(); if (r >= 0) { tableModel.removeRow(r); clearForm(); } }

    private void clearForm() {
        reservationIdField.setText(""); reservationDateField.setText(""); checkInDateField.setText("");
        checkOutDateField.setText(""); totalAmountField.setText(""); roomsField.setText("");
        reservationTable.clearSelection();
    }

    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new ReservationGUI().setVisible(true)); }
}


