package com.hotel.ui;

import com.hotel.dao.BookingDAO;
import com.hotel.model.Booking;

import javax.swing.*;
import java.sql.Date;

public class BookRoomFrame extends JFrame {

    private JTextField customerIdField;
    private JTextField roomIdField;
    private JTextField checkInField;
    private JTextField checkOutField;

    public BookRoomFrame() {

        setTitle("Book Room");
        setSize(350, 320);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdLabel.setBounds(30, 30, 100, 25);
        add(customerIdLabel);

        customerIdField = new JTextField();
        customerIdField.setBounds(140, 30, 150, 25);
        add(customerIdField);

        JLabel roomIdLabel = new JLabel("Room ID:");
        roomIdLabel.setBounds(30, 70, 100, 25);
        add(roomIdLabel);

        roomIdField = new JTextField();
        roomIdField.setBounds(140, 70, 150, 25);
        add(roomIdField);

        JLabel checkInLabel = new JLabel("Check-in (YYYY-MM-DD):");
        checkInLabel.setBounds(30, 110, 200, 25);
        add(checkInLabel);

        checkInField = new JTextField();
        checkInField.setBounds(30, 135, 260, 25);
        add(checkInField);

        JLabel checkOutLabel = new JLabel("Check-out (YYYY-MM-DD):");
        checkOutLabel.setBounds(30, 170, 200, 25);
        add(checkOutLabel);

        checkOutField = new JTextField();
        checkOutField.setBounds(30, 195, 260, 25);
        add(checkOutField);

        JButton bookButton = new JButton("Book Room");
        bookButton.setBounds(100, 235, 130, 30);
        add(bookButton);

        bookButton.addActionListener(e -> bookRoom());

        setVisible(true);
    }

    private void bookRoom() {
        try {
            int customerId = Integer.parseInt(customerIdField.getText());
            int roomId = Integer.parseInt(roomIdField.getText());
            Date checkIn = Date.valueOf(checkInField.getText());
            Date checkOut = Date.valueOf(checkOutField.getText());

            Booking booking = new Booking(customerId, roomId, checkIn, checkOut);
            BookingDAO dao = new BookingDAO();

            boolean success = dao.addBooking(booking);

            if (success) {
                JOptionPane.showMessageDialog(this, "Room booked successfully");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Room not available for selected dates");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input or date format");
        }
    }
}
