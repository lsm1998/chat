/*
 * 作者：刘时明
 * 时间：2020/3/21-14:43
 * 作用：
 */
package com.lsm1998.chat.dao;

import com.lsm1998.chat.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao
{
    @Autowired
    private MongoTemplate mongoTemplate;

    public String findAesKey(Long id)
    {
        User user = mongoTemplate.findById(id, User.class);
        return user == null ? null : user.getAesKey();
    }

    public User find(Long id)
    {
        return mongoTemplate.findById(id, User.class);
    }
}
