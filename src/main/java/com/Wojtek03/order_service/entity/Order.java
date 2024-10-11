package com.Wojtek03.order_service.entity;

import com.Wojtek03.order_service.status.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    @Embedded
    private CustomerInfo customerInfo;
    private BigDecimal totalAmount;
}

