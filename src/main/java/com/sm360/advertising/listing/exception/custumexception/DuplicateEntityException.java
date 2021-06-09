package com.sm360.advertising.listing.exception.custumexception;

public class DuplicateEntityException extends RuntimeException {

    public DuplicateEntityException(String msg) {
        super(msg);
    }
}