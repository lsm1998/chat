/*
 * 作者：刘时明
 * 时间：2020/3/19-23:27
 * 作用：
 */
package com.lsm1998.chat;

import com.lsm1998.chat.ui.LoginPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChatClient
{
    public static void main(String[] args)
    {
        // JVM启动参数  => -Djava.awt.headless=false
        ConfigurableApplicationContext context = SpringApplication.run(ChatClient.class, args);
        LoginPage loginPage = context.getBean(LoginPage.class);
        loginPage.showPage();
    }
}
