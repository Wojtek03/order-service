package com.Wojtek03.order_service.exception;

import org.springframework.http.HttpStatus;

public class OrderServiceException extends RuntimeException {
    private final HttpStatus httpStatus;

    public OrderServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

