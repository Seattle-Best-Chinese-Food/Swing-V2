package com.example.swing.dao;

import com.example.swing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class AuthDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email, password, role) VALUES (?, ?, ?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    public User findUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try {
            List<User> users = jdbcTemplate.query(con -> {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, email);
                return ps;
            }, (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            });

            if (users.isEmpty()) {
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            System.err.println("Error finding user by email: " + e.getMessage());
            return null;
        }
    }
}

