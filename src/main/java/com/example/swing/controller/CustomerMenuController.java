package com.example.swing.controller;

import com.example.swing.dao.DishDAO;
import com.example.swing.model.Dish;
import com.example.swing.view.CustomerMenuView;
import com.example.swing.view.OrderPopup;
import com.example.swing.dao.OrderDAO;
import com.example.swing.dao.OrderItemDAO;

import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMenuController {
    
    // private DishDAO dishDAO;
    // private List<Dish> dishes;
    // private List<Dish> cart;
    // private CustomerMenuView view;
    // private OrderPopup orderPopup;
    private DishDAO dishDAO;
    private CustomerMenuView view;
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private OrderPopup orderPopup;
    private List<Dish> cart = new ArrayList<>();
    private List<Dish> dishes;

    @Autowired
    public CustomerMenuController(DishDAO dishDAO, CustomerMenuView view, OrderDAO orderDAO, OrderItemDAO orderItemDAO) {
        // this.dishDAO = dishDAO;
        // this.cart = new ArrayList<>();
        // this.view = view;
        // this.orderPopup = new OrderPopup(view.getFrame(), orderDAO, orderItemDAO);
        // loadDishes();
        // setupViewOrderButton();
        this.dishDAO = dishDAO;
        this.view = view;
        this.orderDAO = orderDAO;
        this.orderItemDAO = orderItemDAO;
        // this.orderPopup = new OrderPopup(view.getFrame(), orderDAO, orderItemDAO);
    }

    public void initialize() {
        System.out.println("Initializing CustomerMenuController...");
        view.initialize();
        this.orderPopup = new OrderPopup(view.getFrame(), orderDAO, orderItemDAO); // 延迟初始化 OrderPopup
        // setupViewOrderButton();
        loadDishes();
        System.out.println("CustomerMenuController initialization complete.");
    }

    private void loadDishes() {
        try {
            dishes = dishDAO.getAll();
            if (dishes == null || dishes.isEmpty()) {
                System.out.println("No dishes found in the database.");
                return;
            }
            for (Dish dish : dishes) {
                ActionListener orderAction = e -> addDishToCart(dish);
                JPanel card = view.createDishCard(dish, orderAction);
                view.addDishCard(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load dishes.");
        }
    }
    

    private void addDishToCart(Dish dish) {
        cart.add(dish);
        view.getViewOrderButton().setText("View Order (" + cart.size() + ")");
        JButton orderButton = view.getOrderButton(dish);
        if (orderButton == null) {
            System.out.println("Order button not found for dish: " + dish.getName());
            return;
        }
        orderButton.setText("Success");
        orderButton.setEnabled(false);
    }
    

    private void setupViewOrderButton() {
        view.getViewOrderButton().addActionListener(e -> orderPopup.showOrderPopup(cart));
    }
}
