/*
 * 作者：刘时明
 * 时间：2020/3/22-12:00
 * 作用：
 */
package com.lsm1998.chat.service;

import com.lsm1998.chat.utils.HTTPClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;

@Component
@Slf4j
public class HttpService
{
    private static final String LOGIN_URL = "http://localhost:8080/auth/login";

    private static final String AUTH_KEY = "Authorization";

    private static final int OK = 200;

    private String token;

    public boolean login(String username, String password)
    {
        String json = String.format("{\"username\": \"%s\", \"password\": \"%s\",\"rememberMe\":true}", username, password);
        try
        {
            HttpResponse<String> response = HTTPClientUtil.post(LOGIN_URL, json, null);
            if (response.statusCode() == OK)
            {
                response.headers().map().forEach((k, v) ->
                {
                    if (AUTH_KEY.equals(k) && v.size() > 0)
                    {
                        token = v.get(0);
                    }
                });
                return true;
            }
        } catch (Exception e)
        {
            log.error("登录请求失败,err={}", e.getMessage());
            return false;
        }
        return false;
    }
}
