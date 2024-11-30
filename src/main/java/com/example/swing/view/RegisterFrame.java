package com.example.swing.view;

import com.example.swing.service.AuthService;
import com.example.swing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class RegisterFrame extends JFrame {

    @Autowired
    private AuthService authService;

    public RegisterFrame() {
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 30, 100, 25);
        add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(150, 30, 200, 25);
        add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 70, 100, 25);
        add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(150, 70, 200, 25);
        add(lastNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 110, 100, 25);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 110, 200, 25);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 100, 25);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 150, 200, 25);
        add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 200, 100, 25);
        add(registerButton);

        // 注册逻辑
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    // 使用 User 模型封装数据
                    User user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setRole("customer"); // 默认注册为普通用户

                    boolean success = authService.register(user); // 调用服务层方法

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Registration successful!");
                        dispose(); // 关闭注册界面
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration failed. Email might already exist.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error occurred while registering.");
                }
            }
        });
    }
}

