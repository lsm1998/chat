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
    /**
     * 启动入口类
     */
    private final static Class<? extends BasePage> START_CLASS = LoginPage.class;

    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = SpringApplication.run(ChatClient.class, appendArgs(args));
        BasePage mainPage = context.getBean(ChatClient.START_CLASS);
        mainPage.showPage();
    }

    /**
     * SpringBoot结合使用Swing需要追加启动参数
     *
     * @param args
     * @return
     */
    private static String[] appendArgs(String[] args)
    {
        String[] temp=new String[args.length+1];
        System.arraycopy(args,0,temp,0,args.length);
        temp[args.length] = "-Djava.awt.headless=false";
        return temp;
    }
}
