/*
 * 作者：刘时明
 * 时间：2020/3/20-22:15
 * 作用：
 */
package com.lsm1998.chat.handler.bytes;

import com.lsm1998.chat.ChatServer;
import com.lsm1998.chat.handler.OnLineListService;
import com.lsm1998.chat.proto.DataProto;
import com.lsm1998.chat.utils.ProtoBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
public class BytesHandler extends ChannelInboundHandlerAdapter
{
    private BytesRequestHandler handler;
    private OnLineListService onLineListService;

    public BytesHandler()
    {
        this.handler = ChatServer.context.getBean(BytesRequestHandler.class);
        this.onLineListService = ChatServer.context.getBean(OnLineListService.class);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.getBytes(0, bytes);
        buf.release();

        DataProto.MsgReq req = ProtoBufUtil.parseReqBytes(bytes);
        if (req != null)
        {
            handler.service(req, ctx);
        } else
        {
            String str="echos => "+new String(bytes);
            ctx.writeAndFlush(Unpooled.copiedBuffer(str.getBytes()));
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception
    {
        onLineListService.leave(ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        log.debug("一名用户断开连接,name={}", ctx.name());
    }
}
