package com.v1.SmartCapital.enums;

public enum UserStatus {
    ASSIGNED("Assigned"),
    UNASSIGNED("Unassigned");
    private String value;

    UserStatus(String value){
        this.value=value;
    }
    public String value(){
        return value;
    }
}
