import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RoomGUI extends JFrame {

    private JTextField roomIdField;
    private JTextField roomNumberField;
    private JComboBox<String> roomTypeCombo;
    private JComboBox<String> bedTypeCombo;
    private JTextField basePriceField;
    private JTextField extraChargeField;
    private JTextField maxOccupancyField;

    private JTable roomTable;
    private DefaultTableModel tableModel;

    private RoomDAO roomDAO;

    public RoomGUI() {

        roomDAO = new RoomDAO();

        setTitle("Hotel Reservation System - Room Management");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel = new JLabel(
                "ROOM MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        // =====================================================
        // FORM PANEL
        // =====================================================

        JPanel formPanel = new JPanel(
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

        // =====================================================
        // ROOM ID
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Room ID:"),
                gbc
        );

        roomIdField =
                new JTextField(15);

        gbc.gridx = 1;

        formPanel.add(
                roomIdField,
                gbc
        );

        // =====================================================
        // ROOM NUMBER
        // =====================================================

        gbc.gridx = 2;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Room Number:"),
                gbc
        );

        roomNumberField =
                new JTextField(15);

        gbc.gridx = 3;

        formPanel.add(
                roomNumberField,
                gbc
        );

        // =====================================================
        // ROOM TYPE
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Room Type:"),
                gbc
        );

        roomTypeCombo =
                new JComboBox<>(
                        new String[]{
                            "Standard",
                            "Deluxe"
                        }
                );

        gbc.gridx = 1;

        formPanel.add(
                roomTypeCombo,
                gbc
        );

        // =====================================================
        // BED TYPE
        // =====================================================

        gbc.gridx = 2;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Bed Type:"),
                gbc
        );

        bedTypeCombo =
                new JComboBox<>(
                        new String[]{
                            "Single",
                            "Double",
                            "Queen",
                            "King"
                        }
                );

        gbc.gridx = 3;

        formPanel.add(
                bedTypeCombo,
                gbc
        );

        // =====================================================
        // BASE PRICE
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Base Price (RM):"),
                gbc
        );

        basePriceField =
                new JTextField(15);

        basePriceField.setEditable(false);

        gbc.gridx = 1;

        formPanel.add(
                basePriceField,
                gbc
        );

        // =====================================================
        // EXTRA CHARGE
        // =====================================================

        gbc.gridx = 2;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Extra Charge (RM):"),
                gbc
        );

        extraChargeField =
                new JTextField(15);

        extraChargeField.setEditable(false);

        gbc.gridx = 3;

        formPanel.add(
                extraChargeField,
                gbc
        );

        // =====================================================
        // MAX OCCUPANCY
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(
                new JLabel("Max Occupancy:"),
                gbc
        );

        maxOccupancyField =
                new JTextField(15);

        gbc.gridx = 1;

        formPanel.add(
                maxOccupancyField,
                gbc
        );

        // =====================================================
        // BUTTONS
        // =====================================================

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
        gbc.gridwidth = 4;

        formPanel.add(
                buttonPanel,
                gbc
        );

        // =====================================================
        // TOP PANEL
        // =====================================================

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

        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {
            "roomId",
            "roomNumber",
            "roomType",
            "bedType",
            "basePrice",
            "extraCharge",
            "maxOccupancy"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

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

        // =====================================================
        // FOOTER
        // =====================================================

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

        // =====================================================
        // ROOM TYPE LISTENER
        // =====================================================

        roomTypeCombo.addActionListener(
                e -> updateRoomPrice()
        );

        // =====================================================
        // BED TYPE LISTENER
        // =====================================================

        bedTypeCombo.addActionListener(
                e -> updateRoomPrice()
        );

        // =====================================================
        // BUTTON LISTENERS
        // =====================================================

        addButton.addActionListener(
                e -> addRow()
        );

        updateButton.addActionListener(
                e -> updateRow()
        );

        deleteButton.addActionListener(
                e -> deleteRow()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        backButton.addActionListener(e -> {

            new DashboardGUI().setVisible(true);

            dispose();
        });

        // =====================================================
        // TABLE SELECTION
        // =====================================================

        roomTable.getSelectionModel()
                .addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && roomTable.getSelectedRow() >= 0) {

                int r =
                        roomTable.getSelectedRow();

                roomIdField.setText(
                        tableModel
                                .getValueAt(r, 0)
                                .toString()
                );

                roomNumberField.setText(
                        tableModel
                                .getValueAt(r, 1)
                                .toString()
                );

                roomTypeCombo.setSelectedItem(
                        tableModel
                                .getValueAt(r, 2)
                                .toString()
                );

                bedTypeCombo.setSelectedItem(
                        tableModel
                                .getValueAt(r, 3)
                                .toString()
                );

                basePriceField.setText(
                        tableModel
                                .getValueAt(r, 4)
                                .toString()
                );

                extraChargeField.setText(
                        tableModel
                                .getValueAt(r, 5)
                                .toString()
                );

                maxOccupancyField.setText(
                        tableModel
                                .getValueAt(r, 6)
                                .toString()
                );
            }
        });

        add(mainPanel);

        // =====================================================
        // INITIAL PRICE
        // =====================================================

        updateRoomPrice();

        // =====================================================
        // LOAD DATABASE RECORDS
        // =====================================================

        loadRooms();
    }

    // =====================================================
    // UPDATE PRICE
    // =====================================================

    private void updateRoomPrice() {

        String bedType =
                bedTypeCombo
                        .getSelectedItem()
                        .toString();

        String roomType =
                roomTypeCombo
                        .getSelectedItem()
                        .toString();

        double basePrice =
                HotelRoom.getBasePriceByBedType(
                        bedType
                );

        double extraCharge = 0.00;

        if (roomType.equalsIgnoreCase("Deluxe")) {

            extraCharge = 80.00;
        }

        basePriceField.setText(
                String.format(
                        "%.2f",
                        basePrice
                )
        );

        extraChargeField.setText(
                String.format(
                        "%.2f",
                        extraCharge
                )
        );

        // Set suggested occupancy
        if (maxOccupancyField.getText().trim().isEmpty()) {

            if (bedType.equals("Single")) {

                maxOccupancyField.setText("1");

            } else if (bedType.equals("Double")) {

                maxOccupancyField.setText("2");

            } else {

                maxOccupancyField.setText("2");
            }
        }
    }

    // =====================================================
    // VALIDATION
    // =====================================================

    private boolean validateInput() {

        String roomId =
                roomIdField.getText().trim();

        String roomNumber =
                roomNumberField.getText().trim();

        String maxOccupancy =
                maxOccupancyField.getText().trim();

        // -------------------------
        // ROOM ID
        // -------------------------

        if (roomId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Room ID.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            roomIdField.requestFocus();

            return false;
        }

        // -------------------------
        // ROOM NUMBER
        // -------------------------

        if (roomNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Room Number.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            roomNumberField.requestFocus();

            return false;
        }

        // -------------------------
        // MAX OCCUPANCY
        // -------------------------

        if (maxOccupancy.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Max Occupancy.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            maxOccupancyField.requestFocus();

            return false;
        }

        try {

            int occupancy =
                    Integer.parseInt(
                            maxOccupancy
                    );

            if (occupancy <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Max Occupancy must be greater than 0.",
                        "Invalid Occupancy",
                        JOptionPane.WARNING_MESSAGE
                );

                maxOccupancyField.requestFocus();

                return false;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Max Occupancy must be a number.",
                    "Invalid Occupancy",
                    JOptionPane.WARNING_MESSAGE
            );

            maxOccupancyField.requestFocus();

            return false;
        }

        return true;
    }

    // =====================================================
    // CREATE DOMAIN OBJECT
    // =====================================================

    private HotelRoom createRoomFromForm() {

        String roomId =
                roomIdField.getText().trim();

        String roomNumber =
                roomNumberField.getText().trim();

        String roomType =
                roomTypeCombo
                        .getSelectedItem()
                        .toString();

        String bedType =
                bedTypeCombo
                        .getSelectedItem()
                        .toString();

        double basePrice =
                Double.parseDouble(
                        basePriceField.getText()
                );

        int maxOccupancy =
                Integer.parseInt(
                        maxOccupancyField
                                .getText()
                                .trim()
                );

        if (roomType.equalsIgnoreCase("Deluxe")) {

            return new DeluxeRoom(
                    roomId,
                    roomNumber,
                    basePrice,
                    bedType,
                    maxOccupancy
            );

        } else {

            return new StandardRoom(
                    roomId,
                    roomNumber,
                    basePrice,
                    bedType,
                    maxOccupancy
            );
        }
    }

    // =====================================================
    // LOAD ROOMS
    // =====================================================

    private void loadRooms() {

        try {

            List<HotelRoom> rooms =
                    roomDAO.getAllRooms();

            tableModel.setRowCount(0);

            for (HotelRoom room : rooms) {

                String roomType;

                if (room instanceof DeluxeRoom) {

                    roomType = "Deluxe";

                } else {

                    roomType = "Standard";
                }

                tableModel.addRow(
                        new Object[]{
                            room.getRoomId(),
                            room.getRoomNumber(),
                            roomType,
                            room.getBedType(),
                            String.format(
                                    "%.2f",
                                    room.getBasePrice()
                            ),
                            String.format(
                                    "%.2f",
                                    room.getExtraCharge()
                            ),
                            room.getMaxOccupancy()
                        }
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading rooms:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // ADD ROOM
    // =====================================================

    private void addRow() {

        if (!validateInput()) {
            return;
        }

        try {

            String roomId =
                    roomIdField.getText()
                            .trim();

            // Check duplicate ID
            HotelRoom existingRoom =
                    roomDAO.getRoomById(roomId);

            if (existingRoom != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room ID already exists.",
                        "Duplicate Room ID",
                        JOptionPane.WARNING_MESSAGE
                );

                roomIdField.requestFocus();

                return;
            }

            // Create Domain Object
            HotelRoom room =
                    createRoomFromForm();

            // Pass object to DAO
            boolean success =
                    roomDAO.addRoom(room);

            if (success) {

                loadRooms();

                JOptionPane.showMessageDialog(
                        this,
                        "Room added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearForm();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to save room.",
                        "Save Error",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error saving room:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // UPDATE ROOM
    // =====================================================

    private void updateRow() {

        int r =
                roomTable.getSelectedRow();

        if (r < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a room to update.",
                    "Selection Required",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        if (!validateInput()) {
            return;
        }

        String oldRoomId =
                tableModel
                        .getValueAt(r, 0)
                        .toString();

        String newRoomId =
                roomIdField.getText()
                        .trim();

        // Do not allow Room ID change
        if (!oldRoomId.equalsIgnoreCase(newRoomId)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Room ID cannot be changed during update.",
                    "Update Error",
                    JOptionPane.WARNING_MESSAGE
            );

            roomIdField.setText(oldRoomId);

            return;
        }

        try {

            HotelRoom room =
                    createRoomFromForm();

            boolean success =
                    roomDAO.updateRoom(room);

            if (success) {

                loadRooms();

                JOptionPane.showMessageDialog(
                        this,
                        "Room updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearForm();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Room was not found in the database.",
                        "Update Error",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error updating room:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // DELETE ROOM
    // =====================================================

    private void deleteRow() {

        int r =
                roomTable.getSelectedRow();

        if (r < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a room to delete.",
                    "Selection Required",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        String roomId =
                tableModel
                        .getValueAt(r, 0)
                        .toString();

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this room?",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            boolean success =
                    roomDAO.deleteRoom(roomId);

            if (success) {

                loadRooms();

                JOptionPane.showMessageDialog(
                        this,
                        "Room deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearForm();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Room was not found in the database.",
                        "Delete Error",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error deleting room:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // CLEAR FORM
    // =====================================================

    private void clearForm() {

        roomIdField.setText("");
        roomNumberField.setText("");

        roomTypeCombo.setSelectedIndex(0);
        bedTypeCombo.setSelectedIndex(0);

        maxOccupancyField.setText("");

        roomTable.clearSelection();

        updateRoomPrice();

        roomIdField.requestFocus();
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new RoomGUI().setVisible(true)
        );
    }
}
