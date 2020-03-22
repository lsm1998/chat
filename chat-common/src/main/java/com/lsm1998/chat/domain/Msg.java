/*
 * 作者：刘时明
 * 时间：2020/3/21-10:35
 * 作用：
 */
package com.lsm1998.chat.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Msg implements Serializable
{
    private Long id;

    private Integer type;

    private Long formId;

    private Long toId;

    private String content;

    private String aesKey;

    private Long createTime;
}
