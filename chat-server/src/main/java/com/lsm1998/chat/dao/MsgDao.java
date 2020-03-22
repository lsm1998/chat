/*
 * 作者：刘时明
 * 时间：2020/3/21-21:01
 * 作用：
 */
package com.lsm1998.chat.dao;

import com.lsm1998.chat.domain.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MsgDao
{
    @Autowired
    private MongoTemplate mongoTemplate;

    public Msg save(Msg msg)
    {
        return mongoTemplate.save(msg);
    }
}
