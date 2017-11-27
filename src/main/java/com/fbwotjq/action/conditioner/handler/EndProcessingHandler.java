package com.fbwotjq.action.conditioner.handler;

import com.fbwotjq.action.conditioner.vo.UserEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by KAKAO on 2017. 11. 22..
 */
@Slf4j
@Component
public class EndProcessingHandler implements GenericHandler<UserEventMessage> {


    @Override
    public Object handle(UserEventMessage userEventMessage, Map<String, Object> map) {

        log.info("###############################################################################################################################################");
        log.info(String.format("[EndProcessingHandler] %s,%s,%s", userEventMessage.getAccountId(), userEventMessage.getPlusFriendId(),
                userEventMessage.getType()));

        return userEventMessage;
    }

}
