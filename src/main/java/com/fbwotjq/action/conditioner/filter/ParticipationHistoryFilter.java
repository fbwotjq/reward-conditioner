package com.fbwotjq.action.conditioner.filter;

import com.fbwotjq.action.conditioner.vo.UserEventMessage;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Created by KAKAO on 2017. 11. 21..
 */
@Component
public class ParticipationHistoryFilter implements MessageSelector {

    

    @Override
    public boolean accept(Message<?> message) {

        UserEventMessage userEventMessage = (UserEventMessage) message.getPayload();
        return !userEventMessage.getAccountId().equals("32434");

        //return false;
    }

   /* @Override
    public boolean accept(UserEventMessage userEventMessage) {

        if(userEventMessage.getAccountId().equals("111")) {
            userEventMessage = null;
        }

        userEventMessage.getAccountId();
        return true;

    }*/

}
