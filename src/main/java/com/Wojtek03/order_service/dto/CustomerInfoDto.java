package com.Wojtek03.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoDto {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String postalCode;
    private String country;
}
