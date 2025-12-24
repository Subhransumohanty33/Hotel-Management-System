package com.hotel.dao;

import com.hotel.util.DBConnection;
import java.sql.*;

public class RoomDAO {

    public void addRoom(String number, String type, double price) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO rooms(room_number,room_type,price,status) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, number);
            ps.setString(2, type);
            ps.setDouble(3, price);
            ps.setString(4, "Available");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
