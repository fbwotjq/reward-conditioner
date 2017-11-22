package com.kakao.reward.conditioner.handler;

import com.kakao.reward.conditioner.vo.AppLoginUserEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.handler.AbstractMessageProducingHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by KAKAO on 2017. 11. 21..
 */
@Slf4j
@Component
public class AppLoginHandler extends AbstractMessageProducingHandler {

    @Resource(name = "defaultOutputChannel")
    private MessageChannel defaultOutputChannel;

    @Override
    protected void handleMessageInternal(Message<?> message) throws Exception {

        AppLoginUserEventMessage appLoginUserEventMessage = (AppLoginUserEventMessage) message.getPayload();

        log.info("###############################################################################################################################################");
        log.info(String.format("[appLoginUserEventMessageChannelHandler] %s,%s,%s,%s", appLoginUserEventMessage.getAccountId(), appLoginUserEventMessage.getPlusFriendId(),
                appLoginUserEventMessage.getType(), appLoginUserEventMessage.getAdId()));

        defaultOutputChannel.send(message);

    }

}
