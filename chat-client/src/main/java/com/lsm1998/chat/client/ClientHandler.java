package com.lsm1998.chat.client;

import com.lsm1998.chat.proto.DataProto;
import com.lsm1998.chat.utils.ProtoBufUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.lsm1998.chat.ui.MainPage.textArea;

/**
 * @program: chat
 * @description:
 * @author: lsm
 * @create: 2020-03-20 18:49
 **/
@Slf4j
public class ClientHandler extends Thread
{
    private static final int MAX_BYTE_LEN = 10 * 1024;

    private ClientSocket socket;
    private volatile boolean isRun = true;
    private InputStream inputStream;

    public ClientHandler(ClientSocket clientSocket)
    {
        this.socket = clientSocket;
        this.inputStream = this.socket.getInputStream();
    }

    @Override
    public void run()
    {
        while (isRun)
        {
            byte[] data = readBytes();
            if (data != null)
            {
                handler(ProtoBufUtil.parseRspBytes(data));
            }
        }
    }

    private void handler(DataProto.MsgRsp rsp)
    {
        if (rsp == null) return;
        System.out.println("收到消息，type=" + rsp.getMsgType());
        switch (rsp.getMsgType())
        {
            case BROADCAST_MSG:
            case UNRECOGNIZED:
            case FILE_SLICES_MSG:
            case SEND_MSG:
                textArea.append("收到消息:");
                textArea.append(rsp.getSendMsgRsp().getContent());
                textArea.append("\r\n");
                break;
            case LEAVE_MSG:
            case SYSTEM_BROADCAST_MSG:
            case HANDSHAKE_MSG:
            case SYSTEM_MSG:
            case FILE_MSG:
            case PONG_MSG:
            case ACK_MSG:
            default:
                break;
        }
    }

    private byte[] readBytes()
    {
        try
        {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[MAX_BYTE_LEN];
            int len = inputStream.read(bytes);
            byteStream.write(bytes, 0, len);
            // 是否还有盈余数据
            while (inputStream.available() > 0)
            {
                len = inputStream.read(bytes);
                byteStream.write(bytes, 0, len);
            }
            return byteStream.toByteArray();
        } catch (IOException e)
        {
            log.error("readBytes error,err={}", e.getMessage());
            isRun = false;
        }
        return null;
    }
}
