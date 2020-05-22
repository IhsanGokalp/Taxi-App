package com.example.TaxiApp.endpoint;

import com.example.TaxiApp.DTO.DriverPlan.DriverPlanCreateDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanUpdateSeatsDto;
import com.example.TaxiApp.Entity.Order.DriverPlan;

public interface DriverPlanEndpoint {
    DriverPlanDto save(DriverPlanCreateDto driverPlan, Long id);
    DriverPlanDto findById(Long id);
    DriverPlanDto updateSeats(DriverPlanUpdateSeatsDto dto);
}
