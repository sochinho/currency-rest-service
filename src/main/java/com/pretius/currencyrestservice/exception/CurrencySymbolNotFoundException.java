package com.pretius.currencyrestservice.exception;

import org.springframework.http.HttpStatus;

public class CurrencySymbolNotFoundException extends BaseException {

    private static final String errorMessage = "Currency symbol not found.";

    public CurrencySymbolNotFoundException() {
        super(errorMessage, HttpStatus.NOT_FOUND);
    }
}
