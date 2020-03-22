/*
 * 作者：刘时明
 * 时间：2020/3/22-10:50
 * 作用：
 */
package com.lsm1998.chat.service.impl;

import com.lsm1998.chat.domain.User;
import com.lsm1998.chat.service.UserService;
import com.lsm1998.chat.utils.PasswordUtil;
import com.lsm1998.chat.utils.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private Snowflake snowflake;

    @Override
    public User findUserByUserName(String username)
    {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User find(Long id)
    {
        return mongoTemplate.findById(id, User.class);
    }

    @Override
    public void saveUser(User user)
    {
        user.setId(snowflake.nextId());
        user.setRoles("USER");
        user.setSalt(PasswordUtil.getSalt());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        mongoTemplate.save(user);
    }
}
