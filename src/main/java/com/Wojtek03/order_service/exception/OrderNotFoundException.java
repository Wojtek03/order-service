package com.Wojtek03.order_service.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends OrderServiceException {
    public OrderNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

