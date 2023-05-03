package com.spring.security.enums;


public enum CustomEnums {
    OK("ok", "OK"),
    NOT_FOUND("not_found", " NOT_FOUND"),
    SOMETHING_WENT_WRONG("Something went wrong","SOMETHING_WENT_WRONG" ),
    FORBIDDEN("Access is denied","FORBIDDEN"),
    NOT_EMPTY("Not Empty","NOT_EMPTY"),
    BAD_REQUEST("Bad Request","BAD_REQUEST" );

    private  String value;
    private  String message;

    CustomEnums(String value, String message) {
        this.value = value;
        this.message = message;
    }
    CustomEnums(String value)
    {
        this.value=value;
    }
    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
