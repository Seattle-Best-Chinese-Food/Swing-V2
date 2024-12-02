package com.example.swing.view;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    private AdminMenuPage adminMenuPage;
    private AdminOrderManagementPage adminOrderManagementPage;

    public AdminDashboard(AdminMenuPage adminMenuPage, AdminOrderManagementPage adminOrderManagementPage) {
        this.adminMenuPage = adminMenuPage;
        this.adminOrderManagementPage = adminOrderManagementPage;
    }

    public void initialize() {
        setTitle("Admin Dashboard");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton orderManagementButton = new JButton("Open Order Management");
        orderManagementButton.addActionListener(e -> adminOrderManagementPage.initialize());

        JButton menuManagementButton = new JButton("Open Menu Management");
        menuManagementButton.addActionListener(e -> adminMenuPage.initialize());

        add(orderManagementButton);
        add(menuManagementButton);

        setVisible(true);
    }
}
