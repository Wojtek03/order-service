package com.Wojtek03.order_service.service;

import com.Wojtek03.order_service.dto.OrderEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaSender {

    @Value("${order-service.new-orders.topic:new-orders}")
    private String newOrdersTopic;

    @Value("${order-service.cancelled-orders.topic:cancelled-orders}")
    private String cancelledOrdersTopic;

    @Autowired
    private KafkaTemplate<String, OrderEventDto> kafkaTemplate;

    public void sendNewOrderEvent(OrderEventDto orderEventDto) {
        log.info("Sending new order event to topic: {}, with data: {}", newOrdersTopic, orderEventDto);
        kafkaTemplate.send(newOrdersTopic, orderEventDto.getOrderId().toString(), orderEventDto);
    }

    public void sendCancelledOrderEvent(OrderEventDto orderEventDto) {
        log.info("Sending cancelled order event to topic: {}, with data: {}", cancelledOrdersTopic, orderEventDto);
        kafkaTemplate.send(cancelledOrdersTopic, orderEventDto.getOrderId().toString(), orderEventDto);
    }
}
