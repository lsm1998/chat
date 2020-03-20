package com.lsm1998.chat.service;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: chat
 * @description:
 * @author: lsm
 * @create: 2020-03-20 18:49
 **/
@Slf4j
public class ClientHandler extends Thread {
    private ClientSocket socket;
    private volatile boolean isRun = true;
    private InputStream inputStream;

    public ClientHandler(ClientSocket clientSocket) {
        this.socket = clientSocket;
        try {
            this.inputStream = this.socket.getSocket().getInputStream();
        } catch (IOException e) {
            log.error("bind服务端地址错误，err={}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (isRun) {
            byte[] data = readBytes();
            if (data != null) {

            }
        }
    }

    private byte[] readBytes() {
        try {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            log.error("readBytes error,err={}", e.getMessage());
            isRun = false;
        }
        return null;
    }
}
