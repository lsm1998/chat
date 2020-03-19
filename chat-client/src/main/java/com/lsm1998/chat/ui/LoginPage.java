/*
 * 作者：刘时明
 * 时间：2020/3/19-23:35
 * 作用：
 */
package com.lsm1998.chat.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class LoginPage extends JFrame
{
    @Autowired
    private MainPage mainPage;

    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JButton registerButton;

    public LoginPage()
    {
        super("登录页面");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void init()
    {
        username = new JTextField();
        password = new JPasswordField();
        loginButton = new JButton("登录");
        registerButton = new JButton("注册");
    }

    public void showPage()
    {
        this.setVisible(true);
    }
}
