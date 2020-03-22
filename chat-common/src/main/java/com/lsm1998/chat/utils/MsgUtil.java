/*
 * 作者：刘时明
 * 时间：2020/3/21-10:51
 * 作用：
 */
package com.lsm1998.chat.utils;

import com.lsm1998.chat.domain.Msg;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsgUtil
{
    public static void decryptMsg(Msg msg, String aesKey)
    {
        try
        {
            String content = DESUtil.decrypt(msg.getContent(), aesKey);
            msg.setContent(content);
        } catch (Exception e)
        {
            log.error("解密失败，err={}", e.getMessage());
        }
    }

    public static void encryptMsg(Msg msg, String aesKey)
    {
        try
        {
            String content = DESUtil.encrypt(msg.getContent(), aesKey);
            msg.setContent(content);
        } catch (Exception e)
        {
            log.error("解密失败，err={}", e.getMessage());
        }
    }
}
