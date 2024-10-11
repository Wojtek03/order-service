package com.Wojtek03.order_service.controller;

import com.Wojtek03.order_service.dto.CustomerInfoDto;
import com.Wojtek03.order_service.dto.OrderDto;
import com.Wojtek03.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{cartId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long cartId,
                                                @RequestBody CustomerInfoDto customerInfoDto) {
        OrderDto orderDto = orderService.createOrder(cartId, customerInfoDto);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto orderDto = orderService.getOrderById(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }
}
