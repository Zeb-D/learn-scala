package com.yd.api.model.enums;

/**
 *
 * @date: 2019-01-14 17:49
 */
public enum UserTypeEnum {

    MOBLIE("1"),
    EMAIL("2"),
    USER_NAME("3")
    ;

    private String value;

    UserTypeEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
