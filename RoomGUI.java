import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RoomGUI extends JFrame {

    private JTextField roomNumberField;
    private JTextField priceField;

    private JComboBox<String> roomTypeComboBox;
    private JComboBox<String> statusComboBox;

    private JTable roomTable;
    private DefaultTableModel tableModel;

    public RoomGUI() {

        setTitle("Hotel Reservation System - Room Management");
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
                        "ROOM MANAGEMENT",
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
                        "Room Information"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(6, 8, 6, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // Room Number
        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Room Number:"),
                gbc
        );

        roomNumberField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                roomNumberField,
                gbc
        );

        // Room Type
        gbc.gridx = 0;
        gbc.gridy = 1;

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

        formPanel.add(
                roomTypeComboBox,
                gbc
        );

        // Price
        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Price (RM):"),
                gbc
        );

        priceField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                priceField,
                gbc
        );

        // Status
        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(
                new JLabel("Status:"),
                gbc
        );

        statusComboBox =
                new JComboBox<>(
                        new String[]{
                                "Available",
                                "Occupied",
                                "Maintenance"
                        }
                );

        gbc.gridx = 1;

        formPanel.add(
                statusComboBox,
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

        // =========================
        // TABLE
        // =========================

        String[] columns = {
                "Room Number",
                "Room Type",
                "Price (RM)",
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

        roomTable =
                new JTable(tableModel);

        roomTable.setRowHeight(25);

        roomTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(roomTable);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Room Records"
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

        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // EVENT HANDLING
        // =========================

        addButton.addActionListener(e -> {
            addRoom();
        });

        updateButton.addActionListener(e -> {
            updateRoom();
        });

        deleteButton.addActionListener(e -> {
            deleteRoom();
        });

        clearButton.addActionListener(e -> {
            clearForm();
        });

        // =========================
        // BACK TO DASHBOARD
        // =========================

        backButton.addActionListener(e -> {

            DashboardGUI dashboardGUI =
                    new DashboardGUI();

            dashboardGUI.setVisible(true);

            dispose();

        });

        // =========================
        // TABLE SELECTION
        // =========================

        roomTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                roomTable
                                        .getSelectedRow();

                        if (row >= 0) {

                            roomNumberField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    0
                                            )
                                            .toString()
                            );

                            roomTypeComboBox
                                    .setSelectedItem(
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            1
                                                    )
                                    );

                            priceField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    2
                                            )
                                            .toString()
                            );

                            statusComboBox
                                    .setSelectedItem(
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            3
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

        String roomNumber =
                roomNumberField
                        .getText()
                        .trim();

        String price =
                priceField
                        .getText()
                        .trim();

        if (
                roomNumber.isEmpty()
                || price.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (
                !roomNumber.matches("\\d+")
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Room number must contain numbers only.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        try {

            double roomPrice =
                    Double.parseDouble(price);

            if (
                    roomPrice <= 0
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Price must be greater than 0.",
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
                    "Price must be a valid number.",
                    "Invalid Price",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    // =========================
    // ADD ROOM
    // =========================

    private void addRoom() {

        if (!validateForm()) {
            return;
        }

        String roomNumber =
                roomNumberField
                        .getText()
                        .trim();

        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String existingRoom =
                    tableModel
                            .getValueAt(i, 0)
                            .toString();

            if (
                    existingRoom.equals(roomNumber)
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room number already exists.",
                        "Duplicate Room",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to add this room?",
                        "Confirm Save",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                JOptionPane.YES_OPTION
        ) {

            tableModel.addRow(
                    new Object[]{
                            roomNumber,
                            roomTypeComboBox
                                    .getSelectedItem(),
                            priceField
                                    .getText()
                                    .trim(),
                            statusComboBox
                                    .getSelectedItem()
                    }
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Room added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();
        }
    }

    // =========================
    // UPDATE ROOM
    // =========================

    private void updateRoom() {

        int row =
                roomTable.getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a room from the table first.",
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
                        "Are you sure you want to update this room?",
                        "Confirm Update",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                JOptionPane.YES_OPTION
        ) {

            tableModel.setValueAt(
                    roomNumberField
                            .getText()
                            .trim(),
                    row,
                    0
            );

            tableModel.setValueAt(
                    roomTypeComboBox
                            .getSelectedItem(),
                    row,
                    1
            );

            tableModel.setValueAt(
                    priceField
                            .getText()
                            .trim(),
                    row,
                    2
            );

            tableModel.setValueAt(
                    statusComboBox
                            .getSelectedItem(),
                    row,
                    3
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Room updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();
        }
    }

    // =========================
    // DELETE ROOM
    // =========================

    private void deleteRoom() {

        int row =
                roomTable.getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a room from the table first.",
                    "No Record Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String roomNumber =
                tableModel
                        .getValueAt(
                                row,
                                0
                        )
                        .toString();

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete room "
                        + roomNumber
                        + "?",
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
                    "Room deleted successfully!",
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

        roomNumberField.setText("");

        priceField.setText("");

        roomTypeComboBox.setSelectedIndex(0);

        statusComboBox.setSelectedIndex(0);

        roomTable.clearSelection();
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            RoomGUI roomGUI =
                    new RoomGUI();

            roomGUI.setVisible(true);

        });
    }
}