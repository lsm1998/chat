/*
 * 作者：刘时明
 * 时间：2020/3/21-1:19
 * 作用：
 */
package com.lsm1998.chat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig
{
//    @PostConstruct
//    public void parseConfig()
//    {
//        if (this.bgm)
//        {
//            sound.play("理想三旬");
//        }
//    }
}
