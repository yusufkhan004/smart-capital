package com.v1.SmartCapital.enums;

public enum UserType {
    INTERNAL("Internal"),
    EXTERNAL("External");

    private String value;

    UserType(String value){
        this.value=value;
    }
    public String value(){
        return value;
    }
}
