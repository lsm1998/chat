/*
 * 作者：刘时明
 * 时间：2020/3/21-13:57
 * 作用：
 */
package com.lsm1998.chat.handler;

import com.lsm1998.chat.proto.DataProto;

/**
 * 消息处理策略接口
 */
public interface HandlerStrategy<E>
{
    DataProto.MsgRsp handler(E req);
}
