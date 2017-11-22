package com.kakao.reward.conditioner.handler;

import com.kakao.reward.conditioner.vo.UserEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * Created by KAKAO on 2017. 11. 22..
 */
@Slf4j
@Component
public class EndProcessingHandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

        UserEventMessage appLoginUserEventMessage = (UserEventMessage) message.getPayload();

        log.info("###############################################################################################################################################");
        log.info(String.format("[EndProcessingHandler] %s,%s,%s", appLoginUserEventMessage.getAccountId(), appLoginUserEventMessage.getPlusFriendId(),
                appLoginUserEventMessage.getType()));

    }

}
