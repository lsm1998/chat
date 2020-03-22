/*
 * 作者：刘时明
 * 时间：2020/3/21-14:21
 * 作用：
 */
package com.lsm1998.chat.handler.strategy;

import com.lsm1998.chat.dao.MsgDao;
import com.lsm1998.chat.dao.UserDao;
import com.lsm1998.chat.domain.Msg;
import com.lsm1998.chat.handler.HandlerStrategy;
import com.lsm1998.chat.proto.DataProto;
import com.lsm1998.chat.utils.MsgUtil;
import com.lsm1998.chat.utils.Snowflake;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMsgStrategy implements HandlerStrategy<DataProto.SendMsgReq>
{
    private static final DataProto.MsgType TYPE = DataProto.MsgType.SEND_MSG;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MsgDao msgDao;
    @Autowired
    private Snowflake snowflake;

    @Override
    public DataProto.MsgRsp handler(DataProto.SendMsgReq req)
    {
        String formAesKey = userDao.findAesKey(req.getFormId());
        String ToAesKey = userDao.findAesKey(req.getToId());
        Msg msg = new Msg();
        BeanUtils.copyProperties(req, msg);
        msg.setId(snowflake.nextId());
        MsgUtil.decryptMsg(msg, formAesKey);
        msgDao.save(msg);
        MsgUtil.encryptMsg(msg, ToAesKey);
        DataProto.SendMsgRsp rsp = DataProto.SendMsgRsp.newBuilder()
                .setContent(msg.getContent())
                .setFormId(req.getFormId())
                .setToId(req.getToId())
                .setType(req.getType())
                .setTimeStamp(req.getTimeStamp())
                .setId(msg.getId())
                .build();
        return DataProto.MsgRsp.newBuilder()
                .setMsgType(TYPE)
                .setSendMsgRsp(rsp)
                .build();
    }
}
