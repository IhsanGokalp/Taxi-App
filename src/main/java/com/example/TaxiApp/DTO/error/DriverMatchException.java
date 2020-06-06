package com.example.TaxiApp.DTO.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverMatchException extends Exception{
    private Long giveDriverId;
    private Long driverPlanId;

    public DriverMatchException(Long giveDriverId, Long driverPlanId) {
        this.giveDriverId = giveDriverId;
        this.driverPlanId = driverPlanId;
    }
}
