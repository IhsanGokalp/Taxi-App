package com.example.TaxiApp.Mapper.Impl;

import com.example.TaxiApp.DTO.DriverPlan.DriverPlanCreateDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.Order.DriverPlan;
import com.example.TaxiApp.Mapper.DriverMapper;
import com.example.TaxiApp.Mapper.DriverPlansMapper;
import org.springframework.stereotype.Service;

@Service
public class DriverPlansMapperImpl implements DriverPlansMapper {

    private final DriverMapper driverMapper;

    public DriverPlansMapperImpl(DriverMapper driverMapper) {
        this.driverMapper = driverMapper;
    }

    @Override
    public DriverPlan toDriverPlan(DriverPlanCreateDto dto, Long id, Driver driver) {
        DriverPlan plan = new DriverPlan();
        plan.setAvailableSeats(dto.getAvailableSeats());
        plan.setDriver(driver);
        plan.setFromLat(dto.getFromLat());
        plan.setFromLon(dto.getFromLon());
        plan.setTime(dto.getTime());
        plan.setToLat(dto.getToLat());
        plan.setToLon(dto.getToLon());
        plan.setNumberOfPassengers(dto.getAvailableSeats());
        return plan;
    }

    @Override
    public DriverPlanDto toDriverPlanDto(DriverPlan plan) {
        DriverPlanDto dto = new DriverPlanDto();
        dto.setDriverPlanId(plan.getId());
        dto.setAvailableSeats(plan.getAvailableSeats());
        dto.setDriver(driverMapper.toDto(plan.getDriver()));
        dto.setFromLat(plan.getFromLat());
        dto.setFromLon(plan.getFromLon());
        dto.setTime(plan.getTime());
        dto.setToLat(plan.getToLat());
        dto.setToLon(plan.getToLon());
        dto.setFilled(plan.isFilled());
        return dto;
    }
}
