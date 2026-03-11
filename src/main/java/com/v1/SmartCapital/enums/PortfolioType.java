package com.v1.SmartCapital.enums;

public enum PortfolioType {
    ACTIVE(1),
    INACTIVE(2),
    SWAP(3),
    SOLD(4);
    private int value;

    PortfolioType(int value) {
        this.value = value;
    }
    public int value() {return value;}

    public static PortfolioType fromValue(int value) throws Exception {
        for (PortfolioType type : PortfolioType.values())
            if (type.value() == value)
                return type;

        throw new Exception("Invalid PortfolioType value: " + value);
    }
}
