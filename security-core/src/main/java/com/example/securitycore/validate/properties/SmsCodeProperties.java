package com.example.securitycore.validate.properties;

/**
 * @author jia
 */
public class SmsCodeProperties {

    private int length = 6;

    private long expireIn = 60;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }


}
