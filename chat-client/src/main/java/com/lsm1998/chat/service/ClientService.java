/*
 * 作者：刘时明
 * 时间：2020/3/19-23:58
 * 作用：
 */
package com.lsm1998.chat.service;

import com.lsm1998.chat.client.ClientHandler;
import com.lsm1998.chat.client.ClientSocket;
import com.lsm1998.chat.proto.DataProto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.Socket;

@Component
@Slf4j
@Getter
public class ClientService
{
    private ClientSocket clientSocket;
    private boolean status;
    @Value("${chat-server.host}")
    private String host;
    @Value("${chat-server.port}")
    private Integer port;

    @PostConstruct
    public void initClient() throws InterruptedException
    {
        Socket socket;
        try
        {
            socket = new Socket(host, port);
        } catch (IOException e)
        {
            log.error("连接服务端错误，err={}", e.getMessage());
            Thread.sleep(3 * 1000);
            initClient();
            return;
        }
        clientSocket = new ClientSocket(socket);
        ClientHandler handler = new ClientHandler(clientSocket);
        handler.start();
        log.info("连接服务端完成");
        status = true;
    }

    public void sendMsg(String msg)
    {
//        DataProto.MsgReq req = DataProto.MsgReq.newBuilder()
//                .setMsgType(DataProto.MsgType.SEND_MSG)
//                .setSendMsgReq(DataProto.SendMsgReq.newBuilder()
//                        .setContent(msg)
//                        .setFormId(1L)
//                        .setToId(558856263816089600L)
//                        .setTimeStamp(System.currentTimeMillis())
//                        .build())
//                .build();
//        clientSocket.send(req.toByteArray());
        clientSocket.send("hello".getBytes());
    }

    public void handShake()
    {
        DataProto.MsgReq req = DataProto.MsgReq.newBuilder()
                .setMsgType(DataProto.MsgType.HANDSHAKE_MSG)
                .setHandShakeReq(DataProto.HandShakeReq.newBuilder()
                        .setToken(String.valueOf(558856263816089600L))
                        .build())
                .build();
        clientSocket.send(req.toByteArray());
    }

    public boolean isConnect()
    {
        return status;
    }
}
