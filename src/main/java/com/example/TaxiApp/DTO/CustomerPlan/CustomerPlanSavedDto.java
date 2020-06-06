package com.example.TaxiApp.DTO.CustomerPlan;

import com.example.TaxiApp.DTO.Customer.CustomerDto;
import com.example.TaxiApp.DTO.Driver.DriverDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerPlanSavedDto {
    private Long customerPlanId;
    private Long customerId;
    private DriverDto driverDto;
    private Double fromLon;
    private Double fromLat;
    private Double toLat;
    private Double toLon;
    private Integer numOfPassengers;
}
