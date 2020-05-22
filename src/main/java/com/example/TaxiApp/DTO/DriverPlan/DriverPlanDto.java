package com.example.TaxiApp.DTO.DriverPlan;

import com.example.TaxiApp.DTO.Driver.DriverDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DriverPlanDto {
    private Long driverPlanId;
    private DriverDto driver;
    private Date time;
    private Double fromLat;
    private Double fromLon;
    private Double toLat;
    private Double toLon;
    private Integer availableSeats;
    private boolean filled;
}
