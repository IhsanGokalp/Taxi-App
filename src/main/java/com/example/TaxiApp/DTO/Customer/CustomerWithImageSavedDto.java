package com.example.TaxiApp.DTO.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerWithImageSavedDto {
    private Long id;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private boolean imageSaved;
}
