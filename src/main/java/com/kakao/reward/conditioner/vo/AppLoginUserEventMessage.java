package com.kakao.reward.conditioner.vo;

import lombok.Data;
import lombok.ToString;

/**
 * Created by KAKAO on 2017. 11. 16..
 */
@ToString
@Data
public class AppLoginUserEventMessage extends UserEventMessage {

    private long adId;

}
