package com.v1.SmartCapital.enums;

public enum Gender {
    Male("Male"),
    Female("Female"),
    Others("Others");
    private String value;

    Gender(String value){
        this.value=value;
    }
    public String value(){
        return value;
    }
}
