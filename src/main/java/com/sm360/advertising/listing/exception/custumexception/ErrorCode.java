package com.sm360.advertising.listing.exception.custumexception;

public enum ErrorCode {
    PUBLISH_LIMIT_REACHED(1000),
    EMPTY_DATA(1001);

    private final int statusCode;

    ErrorCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int value() {
        return statusCode;
    }
}


