package com.fbwotjq.action.conditioner.configuration;

import com.fbwotjq.action.conditioner.filter.ParticipationHistoryFilter;
import com.fbwotjq.action.conditioner.filter.UserActionExpireFilter;
import com.fbwotjq.action.conditioner.handler.*;
import com.fbwotjq.action.conditioner.vo.AppLoginUserEventMessage;
import com.fbwotjq.action.conditioner.vo.UnKnownUserEventMessage;
import com.kakao.reward.conditioner.handler.*;
import com.fbwotjq.action.conditioner.vo.UserEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.FilterEndpointSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.support.Consumer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableIntegration
@IntegrationComponentScan("com.kakao.reward.conditioner")
public class UserEventIntegrationConfiguration {

    @Resource(name = "userEventMessageChannel") MessageChannel userEventMessageChannel;

    @Resource(name = "defaultOutputChannel") MessageChannel defaultOutputChannel;
    @Resource(name = "exceptionChannel") MessageChannel exceptionChannel;
    @Resource(name = "filterDiscardChannel") MessageChannel filterDiscardChannel;

    @Resource(name = "appLoginUserEventMessageChannel") MessageChannel appLoginUserEventMessageChannel;
    @Resource(name = "unKnownUserEventMessage") MessageChannel unKnownUserEventMessage;

    @Resource(name = "chargeProcessChannel") MessageChannel chargeProcessChannel;
    @Resource(name = "completeProcessChannel") MessageChannel completeProcessChannel;

    @Autowired
    ChargeProcessingHandler chargeProcessingHandler;
    @Autowired
    CompleteProcessingHandler completeProcessingHandler;
    @Autowired
    EndProcessingHandler endProcessingHandler;

    @Autowired
    NoneHandler noneHandler;
    @Autowired
    ExceptionHandler exceptionHandler;
    @Autowired FilterDiscardHandler filterDiscardHandler;

    @Autowired
    ParticipationHistoryFilter participationHistoryFilter;
    @Autowired
    UserActionExpireFilter userActionExpireFilter;

    @Bean("routerSpecEnrichHeaders")
    public MessageHeaders routerSpecEnrichHeaders() {

        Map<String, Object> errorChannelDefine = new HashMap<>();
        errorChannelDefine.put(MessageHeaders.ERROR_CHANNEL, "exceptionChannel");
        return new MessageHeaders(errorChannelDefine);

    }

    @Bean("filterEndpointConfigurer")
    public Consumer<FilterEndpointSpec> filterEndpointConfigurer() {
        return new Consumer<FilterEndpointSpec>() {
            @Override
            public void accept(FilterEndpointSpec filterEndpointSpec) {
                filterEndpointSpec.discardChannel(filterDiscardChannel);
            }
        };
    }

    @Bean("userEventFilterFlow")
    public IntegrationFlow userEventFilterFlow() {

        return IntegrationFlows.from(userEventMessageChannel)
            .<UserEventMessage, Class<?>>route(UserEventMessage::getClass, routerRouterSpec ->
                    routerRouterSpec
                    .defaultOutputChannel(unKnownUserEventMessage)
                    .subFlowMapping(AppLoginUserEventMessage.class, subFlowMapping ->
                        subFlowMapping
                        .enrichHeaders(routerSpecEnrichHeaders())
                        .channel(appLoginUserEventMessageChannel)
                        .filter(participationHistoryFilter, filterEndpointConfigurer())
                        .filter(userActionExpireFilter, filterEndpointConfigurer())
                        .channel(defaultOutputChannel)
                    )
                    .subFlowMapping(UnKnownUserEventMessage.class, subFlowMapping ->
                        subFlowMapping
                        .channel(unKnownUserEventMessage)
                        .handle(h -> {

                        })
                    )
            )
            .get();

    }

    @Bean("userEventProcessFlow")
    public IntegrationFlow userEventProcessFlow() {

        return IntegrationFlows.from(defaultOutputChannel)
            .handle(endProcessingHandler)
            .channel(chargeProcessChannel)
            .handle(chargeProcessingHandler)
            .channel(completeProcessChannel)
            .handle(completeProcessingHandler)
            .handle(message -> {

            })
            .get();

    }

    @Bean("exceptionProcessFlow")
    public IntegrationFlow exceptionProcessFlow() {

        return IntegrationFlows.from(exceptionChannel)
            .handle(exceptionHandler)
            .get();

    }

    @Bean("filterDiscardProcessFlow")
    public IntegrationFlow filterDiscardProcessFlow() {

        return IntegrationFlows.from(filterDiscardChannel)
            .handle(filterDiscardHandler)
            .get();

    }

}
