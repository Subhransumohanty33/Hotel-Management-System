package com.hotel.dao;

import com.hotel.util.DBConnection;
import java.sql.*;

public class CustomerDAO {

    public void addCustomer(String name, String phone, String email) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO customers(name,phone,email) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
