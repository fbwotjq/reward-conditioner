package com.kakao.reward.conditioner.configuration;

import com.kakao.reward.conditioner.filter.ParticipationHistoryFilter;
import com.kakao.reward.conditioner.filter.UserActionExpireFilter;
import com.kakao.reward.conditioner.handler.AppLoginHandler;
import com.kakao.reward.conditioner.handler.EndProcessingHandler;
import com.kakao.reward.conditioner.handler.NoneHandler;
import com.kakao.reward.conditioner.vo.AppLoginUserEventMessage;
import com.kakao.reward.conditioner.vo.UnKnownUserEventMessage;
import com.kakao.reward.conditioner.vo.UserEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

@Slf4j
@Configuration
@EnableIntegration
@IntegrationComponentScan("com.kakao.reward.conditioner")
public class UserEventIntegrationConfiguration {

    @Resource(name = "userEventMessageChannel") MessageChannel userEventMessageChannel;
    @Resource(name = "defaultOutputChannel") MessageChannel defaultOutputChannel;
    @Resource(name = "appLoginUserEventMessageChannel") MessageChannel appLoginUserEventMessageChannel;
    @Resource(name = "unKnownUserEventMessage") MessageChannel unKnownUserEventMessage;

    @Autowired AppLoginHandler appLoginHandler;
    @Autowired EndProcessingHandler endProcessingHandler;
    @Autowired NoneHandler noneHandler;

    @Autowired ParticipationHistoryFilter participationHistoryFilter;
    @Autowired UserActionExpireFilter userActionExpireFilter;

    @Bean
    public IntegrationFlow userEventStartPointFlow() {

        return IntegrationFlows.from(userEventMessageChannel)
            .<UserEventMessage, Class<?>>route(UserEventMessage::getClass, routerRouterSpec ->
                    routerRouterSpec
                    .subFlowMapping(AppLoginUserEventMessage.class, subFlowMapping ->
                        subFlowMapping
                        .channel(appLoginUserEventMessageChannel)
                        .filter(participationHistoryFilter)
                        .filter(userActionExpireFilter)
                        .handle(appLoginHandler)
                    )
                    .subFlowMapping(UnKnownUserEventMessage.class, subFlowMapping ->
                        subFlowMapping
                        .channel(unKnownUserEventMessage)
                        .handle(h -> {

                        })
                    ).defaultOutputToParentFlow()
            )
            .get();
    }

    @Bean
    public IntegrationFlow userEventEndPointFlow() {
        return IntegrationFlows.from(defaultOutputChannel)
            .handle(endProcessingHandler)
            .get();
    }


}
