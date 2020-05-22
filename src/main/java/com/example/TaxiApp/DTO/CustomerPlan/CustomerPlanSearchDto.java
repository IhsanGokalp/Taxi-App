package com.example.TaxiApp.DTO.CustomerPlan;

import com.example.TaxiApp.Enums.WhoOrders;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerPlanSearchDto {
    private Date timeFrom;
    private Date timeTo;
    private Double fromLon;
    private Double fromLat;
    private Double toLat;
    private Double toLon;
    private Integer numOfPassengers;
}
