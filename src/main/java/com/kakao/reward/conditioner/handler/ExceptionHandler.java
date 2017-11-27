package com.kakao.reward.conditioner.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * Created by KAKAO on 2017. 11. 23..
 */
@Slf4j
@Component
public class ExceptionHandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

        Object payLoad = message.getPayload();
        String messageString = payLoad.toString();
        log.info(String.format("[ExceptionHandler] ########################################################################################### %s", messageString));
        //log.info(String.format("[ExceptionHandler] %s", exception.getMessage()));

    }

}
