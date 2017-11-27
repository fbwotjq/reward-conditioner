package com.fbwotjq.action.conditioner.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

import java.util.concurrent.Executors;

/**
 * Created by KAKAO on 2017. 11. 21..
 */
@Configuration
@EnableIntegration
public class MessageChannelConfiguration {

    @Bean(value = "userEventMessageChannel")
    public MessageChannel userEventMessageChannel() {
        return new ExecutorChannel(Executors.newFixedThreadPool(10));
    }

    @Bean(value = "appLoginUserEventMessageChannel")
    public MessageChannel appLoginUserEventMessageChannel() {
        return new ExecutorChannel(Executors.newFixedThreadPool(10)); //return new DirectChannel();
    }

    @Bean(value = "unKnownUserEventMessage")
    public MessageChannel unKnownUserEventMessage() {
        return new DirectChannel();
    }

    @Bean(value = "defaultOutputChannel")
    public MessageChannel defaultOutputChannel() {
        return new DirectChannel();
    }

    @Bean(value = "chargeProcessChannel")
    public MessageChannel chargeProcessChannel() {
        return new ExecutorChannel(Executors.newFixedThreadPool(10));
    }

    @Bean(value = "completeProcessChannel")
    public MessageChannel completeProcessChannel() {
        return new ExecutorChannel(Executors.newFixedThreadPool(10));
    }

    @Bean(value = "exceptionChannel")
    public MessageChannel exceptionChannel() {
        return new ExecutorChannel(Executors.newFixedThreadPool(10));
    }

    @Bean(value = "filterDiscardChannel")
    public MessageChannel filterDiscardChannel() {
        return new ExecutorChannel(Executors.newFixedThreadPool(10));
    }

}
