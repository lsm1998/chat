/*
 * 作者：刘时明
 * 时间：2020/3/21-10:39
 * 作用：
 */
package com.lsm1998.chat.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;
import java.util.UUID;

public class DESUtil
{
    /**
     * 算法名称
     */
    private static final String KEY_ALGORITHM = "DES";

    /**
     * 算法名称/加密模式/填充方式
     */
    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

    /**
     * 加密
     * @param data 要加密的数据
     * @return 加密后的字符串，已使用base64进行编码
     */
    public static String encrypt(String data,String key) throws Exception
    {
        //创建秘钥
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
        //加密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] result =  cipher.doFinal(data.getBytes());
        //使用base64进行编码
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * 解密
     * @param data 要解密的数据，已经使用base64进行编码过的
     * @return 解密后的字符转
     */
    public static String decrypt(String data,String key) throws Exception
    {
        byte[] bs = Base64.getDecoder().decode(data);
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] result =  cipher.doFinal(bs);
        return new String(result);
    }

    /**
     * 生成密钥
     * @return
     */
    public static String getKey()
    {
        return UUID.randomUUID().toString().substring(0,8);
    }
}
