package com.Wojtek03.order_service.dto;

import com.Wojtek03.order_service.status.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private List<OrderItemDto> items;
    private CustomerInfoDto customerInfo;
    private BigDecimal totalAmount;
}

