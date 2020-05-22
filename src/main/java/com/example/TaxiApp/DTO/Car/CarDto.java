package com.example.TaxiApp.DTO.Car;

import com.example.TaxiApp.Enums.Color;
import com.example.TaxiApp.Enums.ColorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto {
    private Long id;
    private String licencePlate;
    private Color color;
    private ColorType variationOfColor;
    private String model;
    private String company;
}
