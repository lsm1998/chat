package chat;

import com.lsm1998.chat.domain.Msg;
import com.lsm1998.chat.proto.DataProto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;

/**
 * @program: chat
 * @description:
 * @author: lsm
 * @create: 2020-03-20 12:26
 **/
public class ProtoTest
{
    @Test
    public void test()
    {
        DataProto.MsgReq req = DataProto.MsgReq.newBuilder()
                .setMsgType(DataProto.MsgType.ACK_MSG)
                .setSendMsgReq(DataProto.SendMsgReq.newBuilder()
                        .setContent("hello!")
                        .setFormId(1L)
                        .setToId(10L)
                        .setTimeStamp((int) (System.currentTimeMillis() / 1000))
                        .build())
                .build();
        req.toByteArray();
    }

    @Test
    public void copy()
    {
        DataProto.MsgReq req = DataProto.MsgReq.newBuilder()
                .setMsgType(DataProto.MsgType.ACK_MSG)
                .setSendMsgReq(DataProto.SendMsgReq.newBuilder()
                        .setContent("hello!")
                        .setFormId(1L)
                        .setToId(10L)
                        .setTimeStamp((int) (System.currentTimeMillis() / 1000))
                        .build())
                .build();
        Msg msg=new Msg();
        msg.setId(1L);
        BeanUtils.copyProperties(req.getSendMsgReq(), msg);
        System.out.println(msg);
    }
}
