package com.v1.SmartCapital.enums;

import com.del.app.exception.InvalidException;

public enum PortUploadType {
    New(1),
    Existing(2),
    Additional(3),
    Lender_Loan_Documents(4);

    private int value;

    PortUploadType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static PortUploadType fromValue(int value) throws Exception {
        for (PortUploadType type : PortUploadType.values())
            if (type.value() == value)
                return type;

        throw new Exception("Invalid PortUploadType value: " + value);
    }
}

