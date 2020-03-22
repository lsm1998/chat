/*
 * 作者：刘时明
 * 时间：2020/3/21-12:23
 * 作用：
 */
package com.lsm1998.chat.auto.sound;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.bgm")
@Data
public class SoundProperties
{
    /**
     * 默认配置
     */
    private Boolean play = true;
    private String dir = "static/music";
    private Boolean pool = true;
}
