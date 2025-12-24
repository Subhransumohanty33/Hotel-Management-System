package com.hotel.dao;

import com.hotel.model.Booking;
import com.hotel.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDAO {

    // 1. Check if room is available for given dates
    public boolean isRoomAvailable(int roomId, java.sql.Date checkIn, java.sql.Date checkOut) {
        boolean available = true;

        String sql = """
            SELECT COUNT(*) FROM bookings
            WHERE room_id = ?
            AND (
                (? BETWEEN check_in AND check_out)
                OR
                (? BETWEEN check_in AND check_out)
                OR
                (check_in BETWEEN ? AND ?)
            )
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);
            ps.setDate(2, checkIn);
            ps.setDate(3, checkOut);
            ps.setDate(4, checkIn);
            ps.setDate(5, checkOut);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                available = rs.getInt(1) == 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return available;
    }

    // 2. Add booking
    public boolean addBooking(Booking booking) {
        Connection con = null;
        PreparedStatement bookingPS = null;
        PreparedStatement roomPS = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // Transaction start

            // Check availability
            if (!isRoomAvailable(
                    booking.getRoomId(),
                    booking.getCheckIn(),
                    booking.getCheckOut())) {
                return false;
            }

            // Insert booking
            String bookingSQL = """
                INSERT INTO bookings (customer_id, room_id, check_in, check_out)
                VALUES (?, ?, ?, ?)
            """;

            bookingPS = con.prepareStatement(bookingSQL);
            bookingPS.setInt(1, booking.getCustomerId());
            bookingPS.setInt(2, booking.getRoomId());
            bookingPS.setDate(3, booking.getCheckIn());
            bookingPS.setDate(4, booking.getCheckOut());
            bookingPS.executeUpdate();

            // Update room status
            String roomSQL = "UPDATE rooms SET status = 'Booked' WHERE room_id = ?";
            roomPS = con.prepareStatement(roomSQL);
            roomPS.setInt(1, booking.getRoomId());
            roomPS.executeUpdate();

            con.commit(); // Transaction success
            return true;

        } catch (Exception e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (bookingPS != null) bookingPS.close();
                if (roomPS != null) roomPS.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
