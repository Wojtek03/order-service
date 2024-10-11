package com.Wojtek03.order_service.repository;

import com.Wojtek03.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}