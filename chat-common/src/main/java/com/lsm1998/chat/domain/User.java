/*
 * 作者：刘时明
 * 时间：2020/3/20-23:22
 * 作用：
 */
package com.lsm1998.chat.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable
{
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String salt;

    private String headImg;

    private Integer age;

    private String aesKey;

    private String roles;

    private Long createTime;
}
