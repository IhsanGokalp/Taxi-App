package com.example.TaxiApp.DTO.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private Long id;
    private String firstName;
    private String surname;
    private String phoneNumber;
}
