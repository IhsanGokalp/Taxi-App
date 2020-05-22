package com.example.TaxiApp.Mapper;

import com.example.TaxiApp.DTO.Driver.DriverDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanCreateDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.Order.DriverPlan;

public interface DriverPlansMapper {
    DriverPlan toDriverPlan(DriverPlanCreateDto dto, Long id, Driver driver);
    DriverPlanDto toDriverPlanDto(DriverPlan plan);
}
