package com.example.TaxiApp.endpoint.Impl;

import com.example.TaxiApp.DTO.DriverPlan.DriverPlanCreateDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanUpdateSeatsDto;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.Order.DriverPlan;
import com.example.TaxiApp.Mapper.DriverPlansMapper;
import com.example.TaxiApp.Service.DriverPlansService;
import com.example.TaxiApp.Service.DriverService;
import com.example.TaxiApp.endpoint.DriverPlanEndpoint;
import org.springframework.stereotype.Service;

@Service
public class DriverPlanEndpointImpl implements DriverPlanEndpoint {

    private final DriverPlansMapper driverPlansMapper;
    private final DriverService driverService;
    private final DriverPlansService driverPlansService;

    public DriverPlanEndpointImpl(DriverPlansMapper driverPlansMapper, DriverService driverService, DriverPlansService driverPlansService) {
        this.driverPlansMapper = driverPlansMapper;
        this.driverService = driverService;
        this.driverPlansService = driverPlansService;
    }

    @Override
    public DriverPlanDto save(DriverPlanCreateDto driverPlan, Long id) {
        Driver driver = driverService.findById(id);
        DriverPlan plan = driverPlansService.save(driverPlansMapper.toDriverPlan(driverPlan,id,driver));
        return driverPlansMapper.toDriverPlanDto(plan);
    }

    @Override
    public DriverPlanDto findById(Long id) {
        return driverPlansMapper.toDriverPlanDto(driverPlansService.findById(id));
    }

    @Override
    public DriverPlanDto updateSeats(DriverPlanUpdateSeatsDto dto) {
        return driverPlansMapper.toDriverPlanDto(driverPlansService.updateSeats(dto));
    }

}
