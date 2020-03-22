/*
 * 作者：刘时明
 * 时间：2020/3/19-23:59
 * 作用：
 */
package com.lsm1998.chat.client;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

@Slf4j
@Getter
public class ClientSocket
{
    private OutputStream outputStream;
    private InputStream inputStream;

    public ClientSocket(Socket socket)
    {
        try
        {
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        } catch (IOException e)
        {
            log.error("获取socket Stream错误，err={}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void send(byte[] bytes)
    {
        try
        {
            System.out.println("字节数据="+ Arrays.toString(bytes));
            outputStream.write(bytes);
        } catch (IOException e)
        {
            log.error("发送数据失败，err={}", e.getMessage());
        }
    }
}
