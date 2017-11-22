package com.kakao.reward.conditioner.controller;

import com.kakao.reward.conditioner.vo.AppLoginUserEventMessage;
import com.kakao.reward.conditioner.vo.UnKnownUserEventMessage;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KAKAO on 2017. 11. 16..
 */
@RequestMapping(value = "/temp")
@RestController
public class TempController {

    @Resource(name = "userEventMessageChannel")
    private MessageChannel userEventMessageChannel;

    @RequestMapping(value = "/appLoginUserEvent")
    @ResponseBody public Map<String, Object> appLoginUserEvent(
            @RequestBody AppLoginUserEventMessage appLoginUserEventMessage
    ) {

        Message<AppLoginUserEventMessage> message = MessageBuilder.withPayload(appLoginUserEventMessage).build();
        userEventMessageChannel.send(message);

        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;

    }

    @RequestMapping(value = "/unKnownUserEventMessage")
    @ResponseBody public Map<String, Object> unKnownUserEventMessage(@RequestBody UnKnownUserEventMessage unKnownUserEventMessage) {

        Message<UnKnownUserEventMessage> message = MessageBuilder.withPayload(unKnownUserEventMessage).build();
        userEventMessageChannel.send(message);

        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;

    }

}
