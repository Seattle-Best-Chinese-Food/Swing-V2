package com.example.swing.view;

import com.example.swing.controller.CustomerMenuController;

import com.example.swing.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class LoginFrame extends JFrame {
    private AdminDashboard adminDashboard;

    @Autowired
    private AuthService authService;

    @Autowired
    private RegisterFrame registerFrame;

    @Autowired
    private AdminMenuPage adminMenuPage;

    @Autowired
    private AdminOrderManagementPage adminOrderManagementPage;
    @Autowired
    private CustomerMenuController customerMenuController;

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 30, 100, 25);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 30, 200, 25);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 70, 100, 25);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 70, 200, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 110, 100, 25);
        add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(250, 110, 100, 25);
        add(registerButton);

        // 登录逻辑
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    String role = authService.login(email, password);
                    if (role == null) {
                        JOptionPane.showMessageDialog(null, "Invalid credentials!");
                    } else if ("admin".equals(role)) {
                        adminDashboard = new AdminDashboard(adminMenuPage, adminOrderManagementPage);
                        adminDashboard.initialize();
                        dispose(); // 关闭当前界面
                    } else {
                        customerMenuController.initialize();
                        dispose(); // 关闭当前界面
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error occurred while logging in.");
                }
            }
        });

        // 打开注册界面
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerFrame.setVisible(true); // 仅在点击注册按钮时显示注册界面
            }
        });
    }
}


