/*
 * 作者：刘时明
 * 时间：2020/3/6-21:47
 * 作用：
 */
package com.lsm1998.chat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser
{
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public JwtUser getCurrentUser()
    {
        return (JwtUser) userDetailsService.loadUserByUsername(getCurrentUserName());
    }

    private static String getCurrentUserName()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null)
        {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}
