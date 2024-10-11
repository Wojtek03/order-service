package com.Wojtek03.order_service.mapper;

import com.Wojtek03.order_service.dto.OrderDto;
import com.Wojtek03.order_service.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDTO);
}