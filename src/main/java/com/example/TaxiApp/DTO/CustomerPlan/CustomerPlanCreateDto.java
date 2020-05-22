package com.example.TaxiApp.DTO.CustomerPlan;

import com.example.TaxiApp.Enums.WhoOrders;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.Date;

@Getter
@Setter
public class CustomerPlanCreateDto {
    private Long driverPlanId;
    private Date time;
    private Double fromLat;
    private Double fromLon;
    private Double toLat;
    private Double toLon;
    private WhoOrders passengerType;
    @Min(1)
    private Integer numOfPassengers;
}
