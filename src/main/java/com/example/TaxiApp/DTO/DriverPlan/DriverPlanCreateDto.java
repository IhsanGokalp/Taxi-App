package com.example.TaxiApp.DTO.DriverPlan;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.Date;

@Getter
@Setter
public class DriverPlanCreateDto {
    private Date time;
    private Double fromLat;
    private Double fromLon;
    private Double toLat;
    private Double toLon;
    @Min(1)
    private Integer availableSeats;
}
