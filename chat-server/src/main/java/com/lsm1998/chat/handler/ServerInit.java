/*
 * 作者：刘时明
 * 时间：2020/3/20-21:12
 * 作用：
 */
package com.lsm1998.chat.handler;

import com.lsm1998.chat.enums.ServerType;
import com.lsm1998.chat.handler.bytes.BytesHandler;
import com.lsm1998.chat.handler.ws.WebSocketHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ServerInit extends ChannelInitializer<SocketChannel>
{
    private final String path;
    private final ServerType type;

    public ServerInit(String path, ServerType type)
    {
        this.path = path;
        this.type = type;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception
    {
        ChannelPipeline pipeline = socketChannel.pipeline();
        if (type == ServerType.WS)
        {
            // WS协议基于HTTP，需要HTTP解码器
            pipeline.addLast(new HttpServerCodec());
            // 对写大数据流的支持
            pipeline.addLast(new ChunkedWriteHandler());
            // 对HttpMessage聚合，聚合为FullHttpReq和FullHttpRsp
            pipeline.addLast(new HttpObjectAggregator(1024 * 64));
            // ws服务器处理的协议，指定路由，处理ws握手，ping+pong=心跳
            // ws协议数据以frames传输，不同的数据类型对应不同的frames
            pipeline.addLast(new WebSocketServerProtocolHandler("/" + path));
            // 自定义handler
            pipeline.addLast(new WebSocketHandler());
        } else
        {
            pipeline.addLast(new BytesHandler());
        }
    }
}
