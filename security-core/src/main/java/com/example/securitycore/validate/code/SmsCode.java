package com.example.securitycore.validate.code;

import java.time.LocalDateTime;

/**
 * @author jia
 */
public class SmsCode extends ValidateCode {

    private String id;

    public SmsCode(String code, Long expireTime) {
        super(code, expireTime);
    }

    public SmsCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
