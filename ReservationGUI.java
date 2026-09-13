import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReservationGUI extends JFrame {

    private JTextField guestNameField;
    private JTextField icField;
    private JTextField phoneField;
    private JTextField checkInField;
    private JTextField checkOutField;
    private JTextField guestsField;

    private JComboBox<String> roomTypeComboBox;
    private JComboBox<String> roomNumberComboBox;

    private JTable reservationTable;
    private DefaultTableModel tableModel;

    public ReservationGUI() {

        setTitle("Hotel Reservation System - Reservation");

        setSize(900, 650);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 20, 30
                )
        );

        // =========================
        // HEADER
        // =========================

        JLabel titleLabel = new JLabel(
                "RESERVATION MANAGEMENT",
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

        // =========================
        // FORM PANEL
        // =========================

        JPanel formPanel = new JPanel(
                new GridBagLayout()
        );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Reservation Information"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(5, 8, 5, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // Guest Name
        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Guest Name:"),
                gbc
        );

        guestNameField = new JTextField(20);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                guestNameField,
                gbc
        );

        // IC / Passport
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        formPanel.add(
                new JLabel("IC / Passport:"),
                gbc
        );

        icField = new JTextField(20);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                icField,
                gbc
        );

        // Phone
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        formPanel.add(
                new JLabel("Phone Number:"),
                gbc
        );

        phoneField = new JTextField(20);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                phoneField,
                gbc
        );

        // Check In
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;

        formPanel.add(
                new JLabel("Check-in Date:"),
                gbc
        );

        checkInField = new JTextField(20);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                checkInField,
                gbc
        );

        // Check Out
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;

        formPanel.add(
                new JLabel("Check-out Date:"),
                gbc
        );

        checkOutField = new JTextField(20);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                checkOutField,
                gbc
        );

        // Room Type
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;

        formPanel.add(
                new JLabel("Room Type:"),
                gbc
        );

        roomTypeComboBox =
                new JComboBox<>(
                        new String[]{
                                "Standard",
                                "Deluxe",
                                "Family",
                                "Suite"
                        }
                );

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                roomTypeComboBox,
                gbc
        );

        // Room Number
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;

        formPanel.add(
                new JLabel("Room Number:"),
                gbc
        );

        roomNumberComboBox =
                new JComboBox<>(
                        new String[]{
                                "101",
                                "102",
                                "103",
                                "104",
                                "201",
                                "202",
                                "203",
                                "204"
                        }
                );

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                roomNumberComboBox,
                gbc
        );

        // Number of Guests
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;

        formPanel.add(
                new JLabel("Number of Guests:"),
                gbc
        );

        guestsField = new JTextField(20);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                guestsField,
                gbc
        );

        // =========================
        // FORM BUTTONS
        // =========================

        JPanel formButtonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                8,
                                5
                        )
                );

        JButton calculateButton =
                new JButton("Calculate");

        JButton addButton =
                new JButton("Save");

        JButton updateButton =
                new JButton("Update");

        JButton deleteButton =
                new JButton("Delete");

        JButton clearButton =
                new JButton("Clear");

        formButtonPanel.add(calculateButton);
        formButtonPanel.add(addButton);
        formButtonPanel.add(updateButton);
        formButtonPanel.add(deleteButton);
        formButtonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3;

        formPanel.add(
                formButtonPanel,
                gbc
        );

        // =========================
        // TABLE
        // =========================

        String[] columnNames = {
                "Guest Name",
                "IC / Passport",
                "Phone",
                "Check-in",
                "Check-out",
                "Room Type",
                "Room No.",
                "Guests"
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

        JScrollPane tableScrollPane =
                new JScrollPane(
                        reservationTable
                );

        tableScrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Reservation Records"
                )
        );

        // =========================
        // CENTER PANEL
        // =========================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        centerPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                tableScrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
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
                new JButton("Back to Dashboard");

        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // EVENT HANDLING
        // =========================

        calculateButton.addActionListener(e -> {
            calculatePrice();
        });

        addButton.addActionListener(e -> {
            addReservation();
        });

        updateButton.addActionListener(e -> {
            updateReservation();
        });

        deleteButton.addActionListener(e -> {
            deleteReservation();
        });

        clearButton.addActionListener(e -> {
            clearForm();
        });

        // Back to Dashboard
        backButton.addActionListener(e -> {

            DashboardGUI dashboardGUI =
                    new DashboardGUI();

            dashboardGUI.setVisible(true);

            dispose();

        });

        // =========================
        // TABLE SELECTION
        // =========================

        reservationTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int selectedRow =
                                reservationTable
                                        .getSelectedRow();

                        if (selectedRow >= 0) {

                            guestNameField.setText(
                                    tableModel
                                            .getValueAt(
                                                    selectedRow,
                                                    0
                                            )
                                            .toString()
                            );

                            icField.setText(
                                    tableModel
                                            .getValueAt(
                                                    selectedRow,
                                                    1
                                            )
                                            .toString()
                            );

                            phoneField.setText(
                                    tableModel
                                            .getValueAt(
                                                    selectedRow,
                                                    2
                                            )
                                            .toString()
                            );

                            checkInField.setText(
                                    tableModel
                                            .getValueAt(
                                                    selectedRow,
                                                    3
                                            )
                                            .toString()
                            );

                            checkOutField.setText(
                                    tableModel
                                            .getValueAt(
                                                    selectedRow,
                                                    4
                                            )
                                            .toString()
                            );

                            roomTypeComboBox
                                    .setSelectedItem(
                                            tableModel
                                                    .getValueAt(
                                                            selectedRow,
                                                            5
                                                    )
                                    );

                            roomNumberComboBox
                                    .setSelectedItem(
                                            tableModel
                                                    .getValueAt(
                                                            selectedRow,
                                                            6
                                                    )
                                    );

                            guestsField.setText(
                                    tableModel
                                            .getValueAt(
                                                    selectedRow,
                                                    7
                                            )
                                            .toString()
                            );
                        }
                    }
                });

        add(mainPanel);
    }

    // =========================
    // CALCULATE PRICE
    // =========================

    private double getRoomPrice() {

        String roomType =
                roomTypeComboBox
                        .getSelectedItem()
                        .toString();

        switch (roomType) {

            case "Standard":
                return 120.00;

            case "Deluxe":
                return 180.00;

            case "Family":
                return 250.00;

            case "Suite":
                return 350.00;

            default:
                return 0.00;
        }
    }

    private void calculatePrice() {

        String guestsText =
                guestsField
                        .getText()
                        .trim();

        if (guestsText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the number of guests.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int guests =
                    Integer.parseInt(
                            guestsText
                    );

            if (guests <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Number of guests must be greater than 0.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            double price =
                    getRoomPrice();

            double total =
                    price;

            JOptionPane.showMessageDialog(
                    this,
                    "Room Price: RM "
                    + String.format("%.2f", price)
                    + "\nTotal Price: RM "
                    + String.format("%.2f", total),
                    "Price Calculation",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Number of guests must be a valid number.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // VALIDATION
    // =========================

    private boolean validateForm() {

        String guestName =
                guestNameField
                        .getText()
                        .trim();

        String ic =
                icField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        String checkIn =
                checkInField
                        .getText()
                        .trim();

        String checkOut =
                checkOutField
                        .getText()
                        .trim();

        String guests =
                guestsField
                        .getText()
                        .trim();

        if (
                guestName.isEmpty()
                || ic.isEmpty()
                || phone.isEmpty()
                || checkIn.isEmpty()
                || checkOut.isEmpty()
                || guests.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all required fields.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (
                !phone.matches("\\d+")
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must contain numbers only.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        try {

            int guestCount =
                    Integer.parseInt(
                            guests
                    );

            if (
                    guestCount <= 0
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Number of guests must be greater than 0.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return false;
            }

            if (
                    guestCount > 10
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Number of guests cannot exceed 10.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return false;
            }

        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Number of guests must be a valid number.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    // =========================
    // ADD RESERVATION
    // =========================

    private void addReservation() {

        if (!validateForm()) {
            return;
        }

        String guestName =
                guestNameField
                        .getText()
                        .trim();

        String ic =
                icField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        String checkIn =
                checkInField
                        .getText()
                        .trim();

        String checkOut =
                checkOutField
                        .getText()
                        .trim();

        String roomType =
                roomTypeComboBox
                        .getSelectedItem()
                        .toString();

        String roomNumber =
                roomNumberComboBox
                        .getSelectedItem()
                        .toString();

        String guests =
                guestsField
                        .getText()
                        .trim();

        // Duplicate room check
        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String existingRoom =
                    tableModel
                            .getValueAt(i, 6)
                            .toString();

            if (
                    existingRoom.equals(roomNumber)
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room "
                        + roomNumber
                        + " is already reserved.",
                        "Room Unavailable",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Confirm this reservation?",
                        "Confirm Reservation",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result != JOptionPane.YES_OPTION
        ) {
            return;
        }

        tableModel.addRow(
                new Object[]{
                        guestName,
                        ic,
                        phone,
                        checkIn,
                        checkOut,
                        roomType,
                        roomNumber,
                        guests
                }
        );

        JOptionPane.showMessageDialog(
                this,
                "Reservation added successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();
    }

    // =========================
    // UPDATE RESERVATION
    // =========================

    private void updateReservation() {

        int selectedRow =
                reservationTable.getSelectedRow();

        if (
                selectedRow == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a reservation from the table first.",
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
                        "Are you sure you want to update this reservation?",
                        "Confirm Update",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result != JOptionPane.YES_OPTION
        ) {
            return;
        }

        tableModel.setValueAt(
                guestNameField.getText().trim(),
                selectedRow,
                0
        );

        tableModel.setValueAt(
                icField.getText().trim(),
                selectedRow,
                1
        );

        tableModel.setValueAt(
                phoneField.getText().trim(),
                selectedRow,
                2
        );

        tableModel.setValueAt(
                checkInField.getText().trim(),
                selectedRow,
                3
        );

        tableModel.setValueAt(
                checkOutField.getText().trim(),
                selectedRow,
                4
        );

        tableModel.setValueAt(
                roomTypeComboBox
                        .getSelectedItem(),
                selectedRow,
                5
        );

        tableModel.setValueAt(
                roomNumberComboBox
                        .getSelectedItem(),
                selectedRow,
                6
        );

        tableModel.setValueAt(
                guestsField.getText().trim(),
                selectedRow,
                7
        );

        JOptionPane.showMessageDialog(
                this,
                "Reservation updated successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();
    }

    // =========================
    // DELETE RESERVATION
    // =========================

    private void deleteReservation() {

        int selectedRow =
                reservationTable.getSelectedRow();

        if (
                selectedRow == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a reservation from the table first.",
                    "No Record Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String guestName =
                tableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete reservation for "
                        + guestName
                        + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result == JOptionPane.YES_OPTION
        ) {

            tableModel.removeRow(
                    selectedRow
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation deleted successfully!",
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

        guestNameField.setText("");

        icField.setText("");

        phoneField.setText("");

        checkInField.setText("");

        checkOutField.setText("");

        guestsField.setText("");

        roomTypeComboBox.setSelectedIndex(0);

        roomNumberComboBox.setSelectedIndex(0);

        reservationTable.clearSelection();
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ReservationGUI reservationGUI =
                    new ReservationGUI();

            reservationGUI.setVisible(true);

        });
    }
}