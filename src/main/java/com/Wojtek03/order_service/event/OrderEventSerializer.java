//package com.Wojtek03.order_service.event;
//
//import com.Wojtek03.order_service.entity.OrderEvent;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang3.SerializationException;
//import org.apache.kafka.common.serialization.Serializer;
//
//public class OrderEventSerializer implements Serializer<OrderEvent> {
//    @Override
//    public byte[] serialize(String topic, OrderEvent data) {
//        try {
//            return new ObjectMapper().writeValueAsBytes(data);
//        } catch (Exception e) {
//            throw new SerializationException("Error serializing OrderEvent", e);
//        }
//    }
//}