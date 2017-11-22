package com.kakao.reward.conditioner.filter;

import org.springframework.integration.core.GenericSelector;
import org.springframework.stereotype.Component;

/**
 * Created by KAKAO on 2017. 11. 21..
 */
@Component
public class ParticipationHistoryFilter implements GenericSelector {

    @Override
    public boolean accept(Object o) {
        return true;
    }

}
