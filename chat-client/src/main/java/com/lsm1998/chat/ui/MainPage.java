/*
 * 作者：刘时明
 * 时间：2020/3/19-23:50
 * 作用：
 */
package com.lsm1998.chat.ui;

import com.lsm1998.chat.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MainPage extends JFrame implements BasePage
{
    @Autowired
    private ClientService clientService;
    private JTextField msg;
    private JButton sendButton;
    public static JTextArea textArea = new JTextArea(10, 5);

    public MainPage()
    {
        super("Chat聊天");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        init();
    }

    private void init()
    {
        this.setLayout(null);
        msg = new JTextField();
        msg.setBounds(10, 20, 100, 30);
        sendButton = new JButton("发送");
        sendButton.setBounds(10, 300, 100, 30);
        textArea.setBounds(10, 150, 200, 60);
        this.add(sendButton);
        this.add(msg);
        this.add(textArea);
        sendButton.addActionListener(e ->
        {
            String text = msg.getText();
            if (text != null)
            {
                clientService.sendMsg(text);
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
