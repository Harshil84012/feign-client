package com.demo.enums;


public enum CustomEnums {

    NOT_FOUND("not_found"),
    UNAUTHORIZED("unauthorized"),
    SOMETHING_WENT_WRONG("Something went wrong");

    private final String value;


    CustomEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
