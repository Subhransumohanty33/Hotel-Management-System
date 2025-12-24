package com.hotel.ui;

import com.hotel.dao.CustomerDAO;

import javax.swing.*;

public class AddCustomerFrame extends JFrame {

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;

    public AddCustomerFrame() {

        setTitle("Add Customer");
        setSize(350, 280);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 30, 170, 25);
        add(nameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(30, 70, 80, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(120, 70, 170, 25);
        add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 110, 80, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(120, 110, 170, 25);
        add(emailField);

        JButton saveButton = new JButton("Add Customer");
        saveButton.setBounds(90, 170, 150, 30);
        add(saveButton);

        saveButton.addActionListener(e -> addCustomer());

        setVisible(true);
    }

    private void addCustomer() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Phone are required");
            return;
        }

        CustomerDAO dao = new CustomerDAO();
        dao.addCustomer(name, phone, email);

        JOptionPane.showMessageDialog(this, "Customer added successfully");
        dispose();
    }
}
