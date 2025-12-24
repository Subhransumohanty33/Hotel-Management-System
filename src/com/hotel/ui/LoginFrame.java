package com.hotel.ui;

import javax.swing.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Hotel Management Login");
        setSize(300,200);
        setLayout(null);

        JLabel user = new JLabel("Username:");
        user.setBounds(30,30,80,25);
        add(user);

        JTextField tf = new JTextField();
        tf.setBounds(120,30,120,25);
        add(tf);

        JButton login = new JButton("Login");
        login.setBounds(90,80,100,30);
        add(login);

        login.addActionListener(e -> {
            new Dashboard();
            dispose();
        });

        setVisible(true);
    }
}
