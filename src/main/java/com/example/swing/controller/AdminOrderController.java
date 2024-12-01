package com.example.swing.controller;

import com.example.swing.dao.OrderDAO;
import com.example.swing.model.Order;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AdminOrderController {
    private OrderDAO orderDAO;
    
    @Autowired
    public AdminOrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    public List<Order> getAllOrders() {
        return orderDAO.getOrders();
    }

    public Boolean updateOrderStatus(String orderId, String status) {
        return orderDAO.updateOrderStatus(orderId, status);
    }
}
