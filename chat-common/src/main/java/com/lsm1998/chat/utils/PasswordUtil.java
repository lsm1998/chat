/*
 * 作者：刘时明
 * 时间：2020/3/20-23:27
 * 作用：
 */
package com.lsm1998.chat.utils;

import java.security.MessageDigest;
import java.util.UUID;

public class PasswordUtil
{
    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

    public static String getSalt()
    {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    public static String encryption(String salt, String password)
    {
        return md5(String.format("%s-%s-%s", salt, password, salt));
    }

    public static String md5(String context)
    {
        try
        {
            return toHex(MessageDigest.getInstance("MD5").digest(context.getBytes("UTF-8"))).toLowerCase();
        } catch (Exception e)
        {
            return null;
        }
    }

    private static String toHex(byte[] bytes)
    {
        StringBuilder str = new StringBuilder(bytes.length * 2);
        final int fifteen = 0x0f;
        for (byte b : bytes)
        {
            str.append(HEX_CHARS[(b >> 4) & fifteen]);
            str.append(HEX_CHARS[b & fifteen]);
        }
        return str.toString();
    }
}
