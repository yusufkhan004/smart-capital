package com.v1.SmartCapital.constants;

public class ValidationConstants {

    // This constructor is private to prevent instantiation of the class.
    private ValidationConstants() {}

    /** DATE CONTENT */
    public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
    public static final String ISO_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String DATE_FORMAT = "dd-MMM-yyyy";
    public static final String PRE_GREGORIAN_DATE_FORMAT = "gregorian";
    public static final String POST_GREGORIAN_DATE_FORMAT = "yyyy\\-mm\\-dd;@";


    /** Regular Expression */
    public static final String REGEX_OF_REQUEST_STATUS = "^(CLOSED|REOPEN|PENDING)$";
    public static final String REGEX_OF_REQUEST_TYPE = "^(DATA_REQUEST|USER_CREATION_REQUEST|CHANGE_REQUEST|ACCESS_REPORT_REQUEST)$";
    public static final String REGEX_OF_SEVERITY_TYPE = "^(LOW|MEDIUM|HIGH)$";

    /** User Content */
    public static final String UPPER_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMERIC_CHARACTERS = "0123456789";
    public static final String SPECIAL_CHARACTERS = "!@#$%^&+=*_";
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 16;


    public static final String USER_MOBILE_COUNTRY_CODE = "+91";


}
