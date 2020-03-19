/*
 * 作者：刘时明
 * 时间：2020/3/19-23:58
 * 作用：
 */
package com.lsm1998.chat.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ClientService
{
    @Value("${chat-server.host}")
    private String host;
    @Value("${chat-server.port}")
    private Integer port;

    @PostConstruct
    public void initClient()
    {

    }
}
