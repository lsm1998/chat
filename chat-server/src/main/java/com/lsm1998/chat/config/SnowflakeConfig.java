/*
 * 作者：刘时明
 * 时间：2020/3/21-21:44
 * 作用：
 */
package com.lsm1998.chat.config;

import com.lsm1998.chat.utils.Snowflake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeConfig
{
    /**
     * 雪花算法机器配置
     *
     * @return
     */
    @Bean
    public Snowflake snowflake(@Value("${snowflake.machine-id}") long machineId,
                               @Value("${snowflake.app-id}") long appId)
    {
        return new Snowflake(machineId, appId);
    }
}
