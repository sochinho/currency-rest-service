package com.pretius.currencyrestservice.exception;

import org.springframework.http.HttpStatus;

public class InternalDataFetcherException extends BaseException {

    private static final String errorMessage = "Internal data server exception.";

    public InternalDataFetcherException() {
        super(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
