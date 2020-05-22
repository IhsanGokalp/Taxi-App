package com.example.TaxiApp.DTO.Driver;

import com.example.TaxiApp.DTO.Car.CarDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class DriverDto {
    private Long id;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private List<CarDto> cars;
}
