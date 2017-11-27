package com.fbwotjq.action.conditioner.handler;

import com.fbwotjq.action.conditioner.vo.UserEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * Created by KAKAO on 2017. 11. 27..
 */
@Slf4j
@Component
public class FilterDiscardHandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

        UserEventMessage userEventMessage = (UserEventMessage) message.getPayload();
        log.info(String.format("[ExceptionHandler] ###########################################################################################", userEventMessage.getAccountId()));

    }

}
