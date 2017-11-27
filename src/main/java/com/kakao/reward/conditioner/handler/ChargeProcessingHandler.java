package com.kakao.reward.conditioner.handler;

import com.kakao.reward.conditioner.vo.UserEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by KAKAO on 2017. 11. 21..
 */
@Slf4j
@Component
public class ChargeProcessingHandler implements GenericHandler<UserEventMessage> {

    @Override
    public Object handle(UserEventMessage userEventMessage, Map<String, Object> map) {

        log.info("###############################################################################################################################################");
        log.info(String.format("[ChargeProcessingHandler] %s,%s,%s", userEventMessage.getAccountId(), userEventMessage.getPlusFriendId(),
                userEventMessage.getType()));

        if(userEventMessage.getAccountId().equals("324341"))
            userEventMessage.setAccountId(null);
            userEventMessage.getAccountId().toString();

        return userEventMessage;
    }

}
