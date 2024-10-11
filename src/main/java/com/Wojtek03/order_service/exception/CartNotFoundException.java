package com.Wojtek03.order_service.exception;

import org.springframework.http.HttpStatus;

public class CartNotFoundException extends OrderServiceException {
    public CartNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

