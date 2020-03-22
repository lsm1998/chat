/*
 * 作者：刘时明
 * 时间：2020/3/19-23:27
 * 作用：
 */
package com.lsm1998.chat;

import com.lsm1998.chat.ui.BasePage;
import com.lsm1998.chat.ui.LoginPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChatClient
{
    // 启动入口类
    private final static Class<? extends BasePage> START_CLASS = LoginPage.class;

    public static void main(String[] args)
    {
        // JVM启动参数  => -Djava.awt.headless=false
        ConfigurableApplicationContext context = SpringApplication.run(ChatClient.class, args);
        start(context, START_CLASS);
    }

    private static void start(ApplicationContext context, Class<? extends BasePage> clazz)
    {
        BasePage mainPage = context.getBean(clazz);
        mainPage.showPage();
    }
}
