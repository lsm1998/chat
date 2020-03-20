/*
 * 作者：刘时明
 * 时间：2020/3/19-23:59
 * 作用：
 */
package com.lsm1998.chat.service;

import lombok.Data;

import java.net.Socket;

@Data
public class ClientSocket {
    private Socket socket;

    public ClientSocket(Socket socket) {
        this.socket = socket;
    }
}
