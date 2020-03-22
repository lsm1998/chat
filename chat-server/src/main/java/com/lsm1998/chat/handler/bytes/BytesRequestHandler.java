/*
 * 作者：刘时明
 * 时间：2020/3/21-11:09
 * 作用：
 */
package com.lsm1998.chat.handler.bytes;

import com.lsm1998.chat.handler.HandlerContext;
import com.lsm1998.chat.handler.OnLineListService;
import com.lsm1998.chat.proto.DataProto;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BytesRequestHandler
{
    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private HandlerContext context;
    @Autowired
    private OnLineListService onLineListService;

    public void service(DataProto.MsgReq req, ChannelHandlerContext ctx)
    {
        log.info("收到消息，type=>{}", req.getMsgType());
        DataProto.MsgRsp rsp = context.service(req);
        if (rsp == null)
        {
            // 没有回复处理
            return;
        }
        switch (req.getMsgType())
        {
            case SEND_MSG:
                OnLineListService.UserBean userBean = onLineListService.getUser(req.getSendMsgReq().getToId());
                userBean.getChannel().writeAndFlush(rsp.toByteArray());
                break;
            case HANDSHAKE_MSG:
                Long id = Long.parseLong(req.getHandShakeReq().getToken());
                onLineListService.onLine(ctx, id);
                ctx.writeAndFlush(Unpooled.copiedBuffer(rsp.toByteArray()));
                break;
            default:
                ctx.writeAndFlush(Unpooled.copiedBuffer(rsp.toByteArray()));
                break;
        }
    }
}
