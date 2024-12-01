package com.example.swing.config;

import com.example.swing.controller.AdminMenuController;
import com.example.swing.dao.DishDAO;
import com.example.swing.dao.OrderDAO;
import com.example.swing.dao.OrderItemDAO;
import com.example.swing.view.AdminMenuPage;
import com.example.swing.view.CustomerMenuView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.swing.controller.AdminOrderController;
import com.example.swing.view.AdminOrderManagementPage;

@Configuration
public class AppConfig {

    @Bean
    public AdminMenuPage adminMenuPage(AdminMenuController controller) {
        return new AdminMenuPage(controller);  // 传递构造参数
    }

    @Bean
    public DishDAO dishDAO() {
        return new DishDAO();
    }

    @Bean
    public OrderDAO orderDAO(OrderItemDAO orderItemDAO) {
        return new OrderDAO(orderItemDAO);
    }

    @Bean
    public OrderItemDAO orderItemDAO() {
        return new OrderItemDAO();
    }

    @Bean
    public CustomerMenuView customerMenuView() {
        return new CustomerMenuView();
    }

    @Bean
    public AdminOrderManagementPage adminOrderManagementPage(AdminOrderController controller) {
        return new AdminOrderManagementPage(controller);
    }
}

