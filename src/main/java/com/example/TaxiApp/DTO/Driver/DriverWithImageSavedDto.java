package com.example.TaxiApp.DTO.Driver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverWithImageSavedDto {
    private Long id;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private boolean imageSaved;
}
