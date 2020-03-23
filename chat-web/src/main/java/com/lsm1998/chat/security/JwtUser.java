/*
 * 作者：刘时明
 * 时间：2020/3/5-11:18
 * 作用：
 */
package com.lsm1998.chat.security;

import com.lsm1998.chat.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
public class JwtUser implements UserDetails
{
    private Collection<? extends GrantedAuthority> authorities;
    private User currUser;

    public JwtUser()
    {
    }

    public JwtUser(User user)
    {
        this.authorities = getRoles(user);
        this.currUser=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    @Override
    public String getPassword()
    {
        return currUser.getPassword();
    }

    @Override
    public String getUsername()
    {
        return currUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public static List<SimpleGrantedAuthority> getRoles(User user)
    {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (user.getRoles() == null)
        {
            return authorities;
        }
        Arrays.stream(user.getRoles().split(",")).forEach(role ->
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        return authorities;
    }
}
