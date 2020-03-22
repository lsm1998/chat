/*
 * 作者：刘时明
 * 时间：2020/3/19-23:35
 * 作用：
 */
package com.lsm1998.chat.ui;

import com.lsm1998.chat.service.ClientService;
import com.lsm1998.chat.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class LoginPage extends JFrame implements BasePage
{
    @Autowired
    private ClientService clientService;
    @Autowired
    private MainPage mainPage;
    @Autowired
    private HttpService httpService;

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
        init();
    }

    private void init()
    {
        this.setLayout(null);
        username = new JTextField();
        username.setBounds(20, 40, 100, 30);
        password = new JPasswordField();
        password.setBounds(20, 100, 100, 30);
        loginButton = new JButton("登录");
        loginButton.setBounds(20, 170, 100, 30);
        registerButton = new JButton("注册");
        registerButton.setBounds(220, 170, 100, 30);
        this.add(username);
        this.add(password);
        this.add(loginButton);
        this.add(registerButton);
        loginButton.addActionListener(e ->
        {
            if (clientService.isConnect())
            {
                if (httpService.login(username.getText(), new String(password.getPassword())))
                {
                    clientService.handShake();
                    this.switchPage(this, mainPage);
                } else
                {
                    JOptionPane.showMessageDialog(null, "账户或者密码错误", "登录失败", JOptionPane.ERROR_MESSAGE);
                }
            } else
            {
                JOptionPane.showMessageDialog(null, "连接到服务器失败了", "登录错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        registerButton.addActionListener(e ->
        {
            if (clientService.isConnect())
            {

            } else
            {
                JOptionPane.showMessageDialog(null, "连接到服务器失败了", "注册错误", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    public void showPage()
    {
        this.setVisible(true);
    }

    @Override
    public void closePage()
    {
        this.setVisible(false);
        this.dispose();
    }
}
