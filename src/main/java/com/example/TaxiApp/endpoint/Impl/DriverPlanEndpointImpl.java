package com.example.TaxiApp.endpoint.Impl;

import com.example.TaxiApp.DTO.DriverPlan.DriverPlanCreateDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDeleteDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanUpdateSeatsDto;
import com.example.TaxiApp.DTO.error.DriverMatchException;
import com.example.TaxiApp.DTO.error.ValidationExceptionDto;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.Order.DriverPlan;
import com.example.TaxiApp.Mapper.DriverPlansMapper;
import com.example.TaxiApp.Service.DriverPlansService;
import com.example.TaxiApp.Service.DriverService;
import com.example.TaxiApp.endpoint.DriverPlanEndpoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public DriverPlanDto save(DriverPlanCreateDto driverPlan, Long id) throws DriverMatchException {
        Driver driver = driverService.findById(id);
        DriverPlan plan = driverPlansService.save(driverPlansMapper.
                toDriverPlan(driverPlan,driver));
        if(id != plan.getDriver().getId())
            throw new DriverMatchException(id, plan.getId());
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

    @Override
    public List<DriverPlanDto> findAllByDriverId(Long id) {
        Driver driver = driverService.findById(id);
        return driverPlansService.findAllByDriverId(driver).
                stream().
                map(driverPlansMapper::toDriverPlanDto).
                collect(Collectors.toList());
    }

    @Override
    public DriverPlanDto delete(DriverPlanDeleteDto driverPlanDeleteDto,
                                Long id) throws DriverMatchException {
        DriverPlan delete = driverPlansService.delete(driverPlanDeleteDto.getDriverPanId(),id);

        return driverPlansMapper.toDriverPlanDto(driverPlansService.
                delete(driverPlanDeleteDto.getDriverPanId(),id));
    }

}
