package com.example.swing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.swing.view.LoginFrame;



@SpringBootApplication
public class SwingApplication {

	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SwingApplication.class);
        app.setHeadless(false); // 禁用 headless 模式以支持 Swing
        ApplicationContext context = app.run(args);

        // 通过 Spring 容器加载 LoginFrame 并启动应用
        LoginFrame loginFrame = context.getBean(LoginFrame.class);
        loginFrame.setVisible(true);
    }
}
