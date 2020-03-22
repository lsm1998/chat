/*
 * 作者：刘时明
 * 时间：2020/3/22-10:48
 * 作用：
 */
package com.lsm1998.chat.service;

import com.lsm1998.chat.domain.User;

public interface UserService
{
    User findUserByUserName(String username);

    User find(Long id);

    void saveUser(User user);
}
