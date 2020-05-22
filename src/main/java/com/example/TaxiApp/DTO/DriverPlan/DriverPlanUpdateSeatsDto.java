package com.example.TaxiApp.DTO.DriverPlan;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverPlanUpdateSeatsDto {
    private Long driverPlanId;
    private Integer seats;

    public DriverPlanUpdateSeatsDto(Long driverPlanId, Integer seats) {
        this.driverPlanId = driverPlanId;
        this.seats = seats;
    }
}
