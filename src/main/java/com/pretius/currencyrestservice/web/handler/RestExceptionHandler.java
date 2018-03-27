package com.pretius.currencyrestservice.web.handler;

import com.pretius.currencyrestservice.exception.BaseException;
import com.pretius.currencyrestservice.exception.CurrencySymbolNotFoundException;
import com.pretius.currencyrestservice.exception.InternalDataFetcherException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({InternalDataFetcherException.class, CurrencySymbolNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleNoSuchObject(BaseException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getErrorMessage());
    }
}
