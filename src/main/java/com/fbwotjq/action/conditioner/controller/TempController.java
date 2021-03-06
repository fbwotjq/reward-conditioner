package com.fbwotjq.action.conditioner.controller;

import com.fbwotjq.action.conditioner.vo.UnKnownUserEventMessage;
import com.fbwotjq.action.conditioner.vo.AppLoginUserEventMessage;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
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

        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put(MessageHeaders.ERROR_CHANNEL, "exceptionChannel");
        Message<AppLoginUserEventMessage> message = MessageBuilder.withPayload(appLoginUserEventMessage).copyHeaders(headerMap).build();
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
