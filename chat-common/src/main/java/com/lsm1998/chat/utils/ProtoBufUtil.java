package com.lsm1998.chat.utils;

import com.google.protobuf.InvalidProtocolBufferException;
import com.lsm1998.chat.proto.DataProto;

/**
 * @program: chat
 * @description:
 * @author: lsm
 * @create: 2020-03-20 18:57
 **/
public class ProtoBufUtil {

    public static byte[] to(DataProto.MsgReq msgReq) {
        return msgReq.toByteArray();
    }

    public static DataProto.MsgReq fo(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            return DataProto.MsgReq.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            return null;
        }
    }
}
