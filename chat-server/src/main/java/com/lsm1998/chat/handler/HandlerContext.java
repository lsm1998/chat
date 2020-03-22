/*
 * 作者：刘时明
 * 时间：2020/3/21-14:04
 * 作用：
 */
package com.lsm1998.chat.handler;

import com.lsm1998.chat.handler.strategy.AckStrategy;
import com.lsm1998.chat.handler.strategy.HandShakeStrategy;
import com.lsm1998.chat.handler.strategy.SendMsgStrategy;
import com.lsm1998.chat.proto.DataProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandlerContext
{
    @Autowired
    private AckStrategy ackStrategy;
    @Autowired
    private HandShakeStrategy handShakeStrategy;
    @Autowired
    private SendMsgStrategy sendMsgStrategy;

    public DataProto.MsgRsp service(DataProto.MsgReq req)
    {
        switch (req.getMsgType())
        {
            case ACK_MSG:
                return ackStrategy.handler(null);
            case HANDSHAKE_MSG:
                return handShakeStrategy.handler(req.getHandShakeReq());
            case PONG_MSG:
            case FILE_MSG:
            case SYSTEM_MSG:
            case SYSTEM_BROADCAST_MSG:
            case LEAVE_MSG:
            case SEND_MSG:
                return sendMsgStrategy.handler(req.getSendMsgReq());
            default:
                return null;
        }
    }
}
