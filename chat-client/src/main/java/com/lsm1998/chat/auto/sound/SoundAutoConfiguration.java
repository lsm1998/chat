/*
 * 作者：刘时明
 * 时间：2020/3/21-12:15
 * 作用：
 */
package com.lsm1998.chat.auto.sound;

import com.lsm1998.chat.config.ExecutorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableConfigurationProperties(SoundProperties.class)
@ConditionalOnClass(SoundStart.class)
public class SoundAutoConfiguration
{
    @Autowired
    private SoundProperties soundProperties;
    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Bean
    @ConditionalOnMissingBean(SoundStart.class)
    public SoundStart initSoundStart()
    {
        SoundStart soundStart = new SoundStart();
        soundStart.setSoundProperties(soundProperties);
        executor.execute(soundStart::initStart);
        return soundStart;
    }
}
