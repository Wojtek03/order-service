package com.Wojtek03.order_service.exception;

import org.springframework.http.HttpStatus;

public class CartAlreadyAddedToOrderException extends OrderServiceException {
    public CartAlreadyAddedToOrderException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}

