//package com.Wojtek03.order_service.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.Wojtek03.order_service.dto.CustomerInfoDto;
//import com.Wojtek03.order_service.dto.OrderDto;
//import com.Wojtek03.order_service.service.OrderService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class OrderControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private OrderService orderService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void createOrder_CorrectData_OrderCreated() throws Exception {
//        CustomerInfoDto customerInfoDto = new CustomerInfoDto("John", "Doe", "123 Main St", "City", "12345", "Country");
//        OrderDto orderDto = new OrderDto();
//        orderDto.setId(1L);
//
//        Mockito.when(orderService.createOrder(any(Long.class), any(CustomerInfoDto.class)))
//                .thenReturn(orderDto);
//
//        mockMvc.perform(post("/orders/{cartId}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(customerInfoDto)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(orderDto.getId()));
//    }
//
//    @Test
//    void getOrderById_ReturnsOrder() throws Exception {
//        OrderDto orderDto = new OrderDto();
//        orderDto.setId(1L);
//
//        Mockito.when(orderService.getOrderById(anyLong()))
//                .thenReturn(orderDto);
//
//        mockMvc.perform(get("/orders/{orderId}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(orderDto.getId()));
//    }
//
//    @Test
//    void getAllOrders_ReturnsPageOfOrders() throws Exception {
//        OrderDto orderDto = new OrderDto();
//        orderDto.setId(1L);
//        Pageable pageable = PageRequest.of(0, 10);
//
//        Mockito.when(orderService.getAllOrders(any(Pageable.class)))
//                .thenReturn(new PageImpl<>(List.of(orderDto)));
//
//        mockMvc.perform(get("/orders")
//                        .param("page", "0")
//                        .param("size", "10")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].id").value(orderDto.getId())); // Poprawiona ścieżka
//    }
//}