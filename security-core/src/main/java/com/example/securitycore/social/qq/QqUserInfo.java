package com.example.securitycore.social.qq;

import lombok.Data;
import lombok.ToString;

/**
 * @author jia
 */

@Data
@ToString
public class QqUserInfo {

    private Integer ret;

    private String msg;

    private String nickname;

    private String figureurl;

    private String figureurl1;

    private String figureurl2;

    private String figureurlQq1;

    private String figureurlQq2;

    private String gender;

    private String openId;

    private String isYellowVip;

    private String vip;

    private String yellowVipLevel;

    private String level;

    private String isYellowYearVip;
}
