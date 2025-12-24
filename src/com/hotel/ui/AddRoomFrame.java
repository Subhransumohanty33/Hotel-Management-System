package com.hotel.ui;

import com.hotel.dao.RoomDAO;
import javax.swing.*;

public class AddRoomFrame extends JFrame {

    public AddRoomFrame() {
        setSize(300,250);
        setLayout(null);

        JTextField num = new JTextField();
        JTextField type = new JTextField();
        JTextField price = new JTextField();

        num.setBounds(120,30,120,25);
        type.setBounds(120,70,120,25);
        price.setBounds(120,110,120,25);

        add(new JLabel("Room No")).setBounds(30,30,80,25);
        add(new JLabel("Type")).setBounds(30,70,80,25);
        add(new JLabel("Price")).setBounds(30,110,80,25);

        add(num); add(type); add(price);

        JButton save = new JButton("Save");
        save.setBounds(90,160,100,30);
        add(save);

        save.addActionListener(e -> {
            new RoomDAO().addRoom(
                num.getText(),
                type.getText(),
                Double.parseDouble(price.getText())
            );
            JOptionPane.showMessageDialog(this,"Room Added");
        });

        setVisible(true);
    }
}
