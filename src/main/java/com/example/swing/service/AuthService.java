package com.example.swing.service;

import com.example.swing.dao.AuthDao;
import com.example.swing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthDao authDao;

    /**
     * 注册用户
     * @param user 要注册的用户对象
     * @return 注册是否成功
     */
    public boolean register(User user) {
        // 调用 DAO 层的 registerUser 方法
        try {
            return authDao.registerUser(user);
        } catch (Exception e) {
            System.err.println("Error in register: " + e.getMessage());
            return false; // 注册失败
        }
    }

    /**
     * 根据邮箱查找用户
     * @param email 用户的邮箱
     * @return 找到的用户对象，或 null
     */
    public User findUserByEmail(String email) {
        try {
            return authDao.findUserByEmail(email);
        } catch (Exception e) {
            System.err.println("Error in findUserByEmail: " + e.getMessage());
            return null; // 查找失败
        }
    }

    /**
     * 用户登录逻辑
     * @param email 用户邮箱
     * @param password 用户密码
     * @return 用户角色 (如 "customer" 或 "admin") 或 null 表示登录失败
     * @throws Exception
     */
    public String login(String email, String password) throws Exception {
        // 调用 DAO 层查找用户
        User user = authDao.findUserByEmail(email);

        if (user != null) {
            // 验证密码是否匹配
            if (password.equals(user.getPassword())) {
                return user.getRole(); // 登录成功，返回角色
            } else {
                System.err.println("Incorrect password.");
            }
        } else {
            System.err.println("User not found.");
        }

        return null; // 登录失败
    }
}

