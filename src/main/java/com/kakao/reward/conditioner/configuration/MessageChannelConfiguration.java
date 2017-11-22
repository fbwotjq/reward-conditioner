package com.kakao.reward.conditioner.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

/**
 * Created by KAKAO on 2017. 11. 21..
 */
@Configuration
@EnableIntegration
public class MessageChannelConfiguration {

    @Bean(value = "userEventMessageChannel")
    public MessageChannel userEventMessageChannel() {
        return new DirectChannel();
    }

    @Bean(value = "appLoginUserEventMessageChannel")
    public MessageChannel appLoginUserEventMessageChannel() {
        return new DirectChannel();
    }

    @Bean(value = "unKnownUserEventMessage")
    public MessageChannel unKnownUserEventMessage() {
        return new DirectChannel();
    }

    @Bean(value = "defaultOutputChannel")
    public MessageChannel defaultOutputChannel() {
        return new DirectChannel();
    }

}
