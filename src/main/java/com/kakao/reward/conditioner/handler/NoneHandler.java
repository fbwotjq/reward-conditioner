package com.kakao.reward.conditioner.handler;

import com.kakao.reward.conditioner.vo.UserEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * Created by KAKAO on 2017. 11. 21..
 */
@Slf4j
@Component
public class NoneHandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

        UserEventMessage appLoginUserEventMessage = (UserEventMessage) message.getPayload();

        log.info("###############################################################################################################################################");
        log.info(String.format("[NoneHandler] %s,%s,%s,%s", appLoginUserEventMessage.getAccountId(), appLoginUserEventMessage.getPlusFriendId(),
                appLoginUserEventMessage.getType()));

    }

}
