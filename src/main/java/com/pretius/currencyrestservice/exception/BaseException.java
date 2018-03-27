package com.pretius.currencyrestservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {
    private HttpStatus httpStatus;

    private ErrorMessage errorMessage;

    public BaseException(String message, HttpStatus httpStatus) {
        this.errorMessage = new ErrorMessage(message);
        this.httpStatus = httpStatus;
    }
}
