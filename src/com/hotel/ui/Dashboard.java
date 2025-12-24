package com.hotel.ui;

import javax.swing.*;

public class Dashboard extends JFrame {

    public Dashboard() {
        setTitle("Dashboard");
        setSize(400,300);
        setLayout(null);

        JButton roomBtn = new JButton("Add Room");
        roomBtn.setBounds(120,50,150,30);
        add(roomBtn);

        JButton custBtn = new JButton("Add Customer");
        custBtn.setBounds(120,100,150,30);
        add(custBtn);

        roomBtn.addActionListener(e -> new AddRoomFrame());
        custBtn.addActionListener(e -> new AddCustomerFrame());

        setVisible(true);
    }
}
