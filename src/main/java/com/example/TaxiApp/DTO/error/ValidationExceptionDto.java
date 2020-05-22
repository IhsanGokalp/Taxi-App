package com.example.TaxiApp.DTO.error;

import com.example.TaxiApp.DTO.Driver.DriverDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationExceptionDto extends Exception{
    private Long driverPlanId;
    private DriverDto driverDto;
    private Integer carSits;

    public ValidationExceptionDto(Long driverPlanId, DriverDto driverDto, Integer carSits) {
        this.driverPlanId = driverPlanId;
        this.driverDto = driverDto;
        this.carSits = carSits;
    }
}
