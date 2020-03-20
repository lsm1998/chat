/*
 * 作者：刘时明
 * 时间：2020/3/19-23:58
 * 作用：
 */
package com.lsm1998.chat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Component
@Slf4j
public class ClientService {
    private ClientSocket clientSocket;

    @Value("${chat-server.host}")
    private String host;
    @Value("${chat-server.port}")
    private Integer port;

    @PostConstruct
    public void initClient() {
        Socket socket = new Socket();
        try {
            socket.bind(new InetSocketAddress(host, port));
        } catch (IOException e) {
            log.error("bind服务端地址错误，err={}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        clientSocket = new ClientSocket(socket);
        ClientHandler handler = new ClientHandler(clientSocket);
        handler.start();
    }
}
