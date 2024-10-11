package com.Wojtek03.order_service.client;

import com.Wojtek03.order_service.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service", url = "http://cart-service:8082")
public interface CartServiceClient {

    @GetMapping("/carts/{cartId}")
    CartDto getCartById(@PathVariable("cartId") Long cartId);
}