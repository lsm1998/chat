/*
 * 作者：刘时明
 * 时间：2020/3/19-23:12
 * 作用：
 */
package com.lsm1998.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ChatServer
{
    public static ApplicationContext context;

    public static void main(String[] args)
    {
        context = SpringApplication.run(ChatServer.class, args);
    }
}
