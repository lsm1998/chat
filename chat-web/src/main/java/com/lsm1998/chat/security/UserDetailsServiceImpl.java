/*
 * 作者：刘时明
 * 时间：2020/3/5-11:16
 * 作用：
 */
package com.lsm1998.chat.security;

import com.lsm1998.chat.domain.User;
import com.lsm1998.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        User user = userService.findUserByUserName(userName);
        return user == null ? null : new JwtUser(user);
    }
}
