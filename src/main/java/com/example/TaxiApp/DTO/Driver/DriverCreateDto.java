package com.example.TaxiApp.DTO.Driver;

import com.example.TaxiApp.DTO.Car.CarCreateDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class DriverCreateDto {
    private Long id;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private ArrayList<CarCreateDto> cars;
}
