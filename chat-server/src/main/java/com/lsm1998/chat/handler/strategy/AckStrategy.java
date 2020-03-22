/*
 * 作者：刘时明
 * 时间：2020/3/21-14:21
 * 作用：
 */
package com.lsm1998.chat.handler.strategy;

import com.lsm1998.chat.handler.HandlerStrategy;
import com.lsm1998.chat.proto.DataProto;
import org.springframework.stereotype.Component;

@Component
public class AckStrategy implements HandlerStrategy<DataProto.MsgReq>
{
    @Override
    public DataProto.MsgRsp handler(DataProto.MsgReq req)
    {
        return null;
    }
}
