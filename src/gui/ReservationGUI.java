
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ReservationGUI extends JFrame {

    // =========================
    // GUI COMPONENTS
    // =========================

    private JTextField reservationIdField;
    private JTextField userIdField;
    private JTextField roomIdField;
    private JTextField reservationDateField;
    private JTextField checkInDateField;
    private JTextField checkOutDateField;
    private JTextField totalAmountField;

    private JComboBox<String> statusComboBox;

    private JTable reservationTable;
    private DefaultTableModel tableModel;

    private final ReservationDAO reservationDAO = new ReservationDAO();

    // Date format
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // =========================
    // CONSTRUCTOR
    // =========================

    public ReservationGUI() {

        setTitle("Hotel Reservation Management");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        initializeComponents();
        loadReservations();
    }

    // =========================
    // INITIALIZE GUI
    // =========================

    private void initializeComponents() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        // =========================================
        // FORM PANEL
        // =========================================

        JPanel formPanel =
                new JPanel(new GridBagLayout());

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Reservation Details"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(5, 5, 5, 5);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        // =========================================
        // RESERVATION ID
        // =========================================

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Reservation ID:"),
                gbc
        );

        reservationIdField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                reservationIdField,
                gbc
        );

        // =========================================
        // USER ID
        // =========================================

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("User ID:"),
                gbc
        );

        userIdField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                userIdField,
                gbc
        );

        // =========================================
        // ROOM ID
        // =========================================

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Room ID:"),
                gbc
        );

        roomIdField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                roomIdField,
                gbc
        );

        // =========================================
        // RESERVATION DATE
        // =========================================

        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(
                new JLabel("Reservation Date:"),
                gbc
        );

        reservationDateField =
                new JTextField(20);

        reservationDateField.setToolTipText(
                "Format: YYYY-MM-DD"
        );

        gbc.gridx = 1;

        formPanel.add(
                reservationDateField,
                gbc
        );

        // =========================================
        // CHECK IN DATE
        // =========================================

        gbc.gridx = 0;
        gbc.gridy = 4;

        formPanel.add(
                new JLabel("Check In Date:"),
                gbc
        );

        checkInDateField =
                new JTextField(20);

        checkInDateField.setToolTipText(
                "Format: YYYY-MM-DD"
        );

        gbc.gridx = 1;

        formPanel.add(
                checkInDateField,
                gbc
        );

        // =========================================
        // CHECK OUT DATE
        // =========================================

        gbc.gridx = 0;
        gbc.gridy = 5;

        formPanel.add(
                new JLabel("Check Out Date:"),
                gbc
        );

        checkOutDateField =
                new JTextField(20);

        checkOutDateField.setToolTipText(
                "Format: YYYY-MM-DD"
        );

        gbc.gridx = 1;

        formPanel.add(
                checkOutDateField,
                gbc
        );

        // =========================================
        // STATUS
        // =========================================

        gbc.gridx = 0;
        gbc.gridy = 6;

        formPanel.add(
                new JLabel("Status:"),
                gbc
        );

        statusComboBox =
                new JComboBox<>(
                        new String[]{
                                "Pending",
                                "Confirmed",
                                "Checked-In",
                                "Checked-Out",
                                "Cancelled"
                        }
                );

        gbc.gridx = 1;

        formPanel.add(
                statusComboBox,
                gbc
        );

        // =========================================
        // TOTAL AMOUNT
        // =========================================

        gbc.gridx = 0;
        gbc.gridy = 7;

        formPanel.add(
                new JLabel("Total Amount:"),
                gbc
        );

        totalAmountField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                totalAmountField,
                gbc
        );

        // =========================================
        // BUTTON PANEL
        // =========================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                5
                        )
                );

        JButton addButton =
                new JButton("Add");

        JButton updateButton =
                new JButton("Update");

        JButton deleteButton =
                new JButton("Delete");

        JButton clearButton =
                new JButton("Clear");

        JButton refreshButton =
                new JButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        formPanel.add(
                buttonPanel,
                gbc
        );

        // =========================================
        // TABLE
        // =========================================

        String[] columnNames = {
                "Reservation ID",
                "User ID",
                "Room ID",
                "Reservation Date",
                "Check In",
                "Check Out",
                "Status",
                "Total Amount"
        };

        tableModel =
                new DefaultTableModel(
                        columnNames,
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

        reservationTable =
                new JTable(tableModel);

        reservationTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        reservationTable.setRowHeight(25);

        JScrollPane scrollPane =
                new JScrollPane(
                        reservationTable
                );

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Reservations"
                )
        );

        // =========================================
        // ADD PANELS
        // =========================================

        mainPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =========================================
        // BACK BUTTON
        // =========================================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                5
                        )
                );

        JButton backButton =
                new JButton(
                        "← Back to Dashboard"
                );

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

        add(mainPanel);

        // =========================================
        // BUTTON ACTIONS
        // =========================================

        addButton.addActionListener(
                e -> addReservation()
        );

        updateButton.addActionListener(
                e -> updateReservation()
        );

        deleteButton.addActionListener(
                e -> deleteReservation()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        refreshButton.addActionListener(
                e -> loadReservations()
        );

        backButton.addActionListener(
                e -> backToDashboard()
        );

        // =========================================
        // TABLE SELECTION
        // =========================================

        reservationTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int selectedRow =
                                reservationTable
                                        .getSelectedRow();

                        if (selectedRow >= 0) {

                            loadSelectedReservation(
                                    selectedRow
                            );
                        }
                    }
                });
    }

    // =========================
    // LOAD RESERVATIONS
    // =========================

    private void loadReservations() {

        tableModel.setRowCount(0);

        List<Reservation> reservations =
                reservationDAO.getAllReservations();

        for (Reservation reservation : reservations) {

            tableModel.addRow(
                    new Object[]{
                            reservation.getReservationId(),
                            reservation.getUserId(),
                            reservation.getRoomId(),
                            reservation.getReservationDate(),
                            reservation.getCheckInDate(),
                            reservation.getCheckOutDate(),
                            reservation.getStatus(),
                            String.format(
                                    "%.2f",
                                    reservation.getTotalAmount()
                            )
                    }
            );
        }
    }

    // =========================
    // LOAD SELECTED RESERVATION
    // =========================

    private void loadSelectedReservation(
            int selectedRow
    ) {

        reservationIdField.setText(
                tableModel
                        .getValueAt(selectedRow, 0)
                        .toString()
        );

        userIdField.setText(
                tableModel
                        .getValueAt(selectedRow, 1)
                        .toString()
        );

        roomIdField.setText(
                tableModel
                        .getValueAt(selectedRow, 2)
                        .toString()
        );

        reservationDateField.setText(
                tableModel
                        .getValueAt(selectedRow, 3)
                        .toString()
        );

        checkInDateField.setText(
                tableModel
                        .getValueAt(selectedRow, 4)
                        .toString()
        );

        checkOutDateField.setText(
                tableModel
                        .getValueAt(selectedRow, 5)
                        .toString()
        );

        statusComboBox.setSelectedItem(
                tableModel
                        .getValueAt(selectedRow, 6)
                        .toString()
        );

        totalAmountField.setText(
                tableModel
                        .getValueAt(selectedRow, 7)
                        .toString()
        );
    }

    // =========================
    // VALIDATE FORM
    // =========================

    private boolean validateReservationData() {

        String reservationId =
                reservationIdField.getText().trim();

        String userId =
                userIdField.getText().trim();

        String roomId =
                roomIdField.getText().trim();

        String reservationDate =
                reservationDateField.getText().trim();

        String checkInDate =
                checkInDateField.getText().trim();

        String checkOutDate =
                checkOutDateField.getText().trim();

        String totalAmountText =
                totalAmountField.getText().trim();

        // =========================================
        // RESERVATION ID
        // =========================================

        if (reservationId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation ID cannot be empty.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            reservationIdField.requestFocus();

            return false;
        }

        // =========================================
        // USER ID
        // =========================================

        if (userId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "User ID cannot be empty.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            userIdField.requestFocus();

            return false;
        }

        // =========================================
        // ROOM ID
        // =========================================

        if (roomId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Room ID cannot be empty.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            roomIdField.requestFocus();

            return false;
        }

        // =========================================
        // RESERVATION DATE
        // =========================================

        if (reservationDate.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation date cannot be empty.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            reservationDateField.requestFocus();

            return false;
        }

        // =========================================
        // CHECK-IN DATE
        // =========================================

        if (checkInDate.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Check-in date cannot be empty.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            checkInDateField.requestFocus();

            return false;
        }

        // =========================================
        // CHECK-OUT DATE
        // =========================================

        if (checkOutDate.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Check-out date cannot be empty.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            checkOutDateField.requestFocus();

            return false;
        }

        // =========================================
        // DATE VALIDATION
        // =========================================

        LocalDate reservationDateValue;
        LocalDate checkInDateValue;
        LocalDate checkOutDateValue;

        try {

            reservationDateValue =
                    LocalDate.parse(
                            reservationDate,
                            DATE_FORMAT
                    );

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid reservation date.\n"
                    + "Please use the format yyyy-MM-dd.\n"
                    + "Example: 2026-09-15",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );

            reservationDateField.requestFocus();

            return false;
        }

        try {

            checkInDateValue =
                    LocalDate.parse(
                            checkInDate,
                            DATE_FORMAT
                    );

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid check-in date.\n"
                    + "Please use the format yyyy-MM-dd.\n"
                    + "Example: 2026-09-15",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );

            checkInDateField.requestFocus();

            return false;
        }

        try {

            checkOutDateValue =
                    LocalDate.parse(
                            checkOutDate,
                            DATE_FORMAT
                    );

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid check-out date.\n"
                    + "Please use the format yyyy-MM-dd.\n"
                    + "Example: 2026-09-20",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );

            checkOutDateField.requestFocus();

            return false;
        }

        // =========================================
        // DATE LOGIC
        // =========================================

        if (checkOutDateValue.isBefore(checkInDateValue)
                || checkOutDateValue.equals(checkInDateValue)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Check-out date must be after "
                    + "the check-in date.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );

            checkOutDateField.requestFocus();

            return false;
        }

        // =========================================
        // RESERVATION DATE LOGIC
        // =========================================

        if (reservationDateValue.isAfter(checkInDateValue)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation date cannot be after "
                    + "the check-in date.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );

            reservationDateField.requestFocus();

            return false;
        }

        // =========================================
        // TOTAL AMOUNT
        // =========================================

        if (totalAmountText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Total amount cannot be empty.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            totalAmountField.requestFocus();

            return false;
        }

        try {

            double amount =
                    Double.parseDouble(
                            totalAmountText
                    );

            if (amount < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Total amount cannot be negative.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );

                totalAmountField.requestFocus();

                return false;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Total amount must be a valid number.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            totalAmountField.requestFocus();

            return false;
        }

        return true;
    }

    // =========================
    // CREATE RESERVATION OBJECT
    // =========================

    private Reservation createReservationFromForm() {

        String reservationId =
                reservationIdField.getText().trim();

        String userId =
                userIdField.getText().trim();

        String roomId =
                roomIdField.getText().trim();

        String reservationDate =
                reservationDateField.getText().trim();

        String checkInDate =
                checkInDateField.getText().trim();

        String checkOutDate =
                checkOutDateField.getText().trim();

        String status =
                statusComboBox
                        .getSelectedItem()
                        .toString();

        double totalAmount =
                Double.parseDouble(
                        totalAmountField
                                .getText()
                                .trim()
                );

        return new Reservation(
                reservationId,
                userId,
                roomId,
                reservationDate,
                checkInDate,
                checkOutDate,
                status,
                totalAmount
        );
    }

    // =========================
    // ADD
    // =========================

    private void addReservation() {

        if (!validateReservationData()) {
            return;
        }

        Reservation reservation =
                createReservationFromForm();

        boolean success =
                reservationDAO.addReservation(
                        reservation
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadReservations();
            clearForm();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to add reservation.\n"
                    + "Please check that the User ID "
                    + "and Room ID exist.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // UPDATE
    // =========================

    private void updateReservation() {

        int selectedRow =
                reservationTable.getSelectedRow();

        if (selectedRow < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a reservation to update.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!validateReservationData()) {
            return;
        }

        Reservation reservation =
                createReservationFromForm();

        boolean success =
                reservationDAO.updateReservation(
                        reservation
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadReservations();
            clearForm();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update reservation.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // DELETE
    // =========================

    private void deleteReservation() {

        int selectedRow =
                reservationTable.getSelectedRow();

        if (selectedRow < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a reservation to delete.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String reservationId =
                tableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete "
                        + "reservation "
                        + reservationId
                        + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirmation !=
                JOptionPane.YES_OPTION) {

            return;
        }

        boolean success =
                reservationDAO.deleteReservation(
                        reservationId
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadReservations();
            clearForm();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete reservation.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // CLEAR FORM
    // =========================

    private void clearForm() {

        reservationIdField.setText("");
        userIdField.setText("");
        roomIdField.setText("");
        reservationDateField.setText("");
        checkInDateField.setText("");
        checkOutDateField.setText("");
        totalAmountField.setText("");

        statusComboBox.setSelectedIndex(0);

        reservationTable.clearSelection();

        reservationIdField.requestFocus();
    }

    // =========================
    // BACK TO DASHBOARD
    // =========================

    private void backToDashboard() {

        dispose();

        SwingUtilities.invokeLater(() -> {

            DashboardGUI dashboard =
                    new DashboardGUI();

            dashboard.setLocationRelativeTo(null);

            dashboard.setVisible(true);
        });
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ReservationGUI gui =
                    new ReservationGUI();

            gui.setLocationRelativeTo(null);

            gui.setVisible(true);
        });
    }
}
