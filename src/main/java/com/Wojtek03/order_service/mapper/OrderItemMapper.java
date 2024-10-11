package com.Wojtek03.order_service.mapper;

import com.Wojtek03.order_service.dto.OrderItemDto;
import com.Wojtek03.order_service.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
    public interface OrderItemMapper {
        OrderItem toDto(OrderItemDto orderItemDto);
        OrderItemDto toEntity(OrderItem orderItem);
    }

