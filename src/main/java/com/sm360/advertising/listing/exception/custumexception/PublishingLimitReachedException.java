package com.sm360.advertising.listing.exception.custumexception;

public class PublishingLimitReachedException extends RuntimeException {

    public PublishingLimitReachedException(String msg) {
        super(msg);
    }
}
