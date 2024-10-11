package com.Wojtek03.order_service.service;

import com.Wojtek03.order_service.client.CartServiceClient;
import com.Wojtek03.order_service.dto.*;
import com.Wojtek03.order_service.entity.CustomerInfo;
import com.Wojtek03.order_service.entity.Order;
import com.Wojtek03.order_service.entity.OrderItem;
import com.Wojtek03.order_service.exception.CartNotFoundException;
import com.Wojtek03.order_service.exception.OrderNotFoundException;
import com.Wojtek03.order_service.mapper.OrderMapper;
import com.Wojtek03.order_service.repository.OrderRepository;
import com.Wojtek03.order_service.status.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartServiceClient cartServiceClient;
    private final OrderMapper orderMapper;
    private final KafkaSender kafkaSender;

    public OrderDto createOrder(Long cartId, CustomerInfoDto customerInfoDto) {
        CartDto cartDto = cartServiceClient.getCartById(cartId);
        if (cartDto == null) {
            throw new CartNotFoundException("Cart with ID " + cartId + " not found.");
        }

        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);

        List<OrderItem> orderItems = cartDto.getItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProductId(cartItem.getProductId());
                    orderItem.setPrice(cartItem.getPrice());
                    orderItem.setQuantity(cartItem.getQuantity());
                    return orderItem;
                })
                .collect(Collectors.toList());

        order.setItems(orderItems);

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setFirstName(customerInfoDto.getFirstName());
        customerInfo.setLastName(customerInfoDto.getLastName());
        customerInfo.setAddress(customerInfoDto.getAddress());
        customerInfo.setCity(customerInfoDto.getCity());
        customerInfo.setPostalCode(customerInfoDto.getPostalCode());
        customerInfo.setCountry(customerInfoDto.getCountry());

        order.setCustomerInfo(customerInfo);

        BigDecimal totalAmount = cartDto.getItems().stream()
                .map(CartItemDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        OrderEventDto orderEventDto = new OrderEventDto(savedOrder.getId(), savedOrder.getStatus().name());
        kafkaSender.sendNewOrderEvent(orderEventDto);

        return orderMapper.toDto(savedOrder);
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found."));

        return orderMapper.toDto(order);
    }
}
