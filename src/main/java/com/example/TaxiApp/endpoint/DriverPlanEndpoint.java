package com.example.TaxiApp.endpoint;

import com.example.TaxiApp.DTO.DriverPlan.DriverPlanCreateDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDeleteDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanUpdateSeatsDto;
import com.example.TaxiApp.DTO.error.DriverMatchException;

import java.util.List;

public interface DriverPlanEndpoint {
    DriverPlanDto save(DriverPlanCreateDto driverPlan, Long id) throws DriverMatchException;
    DriverPlanDto findById(Long id);
    DriverPlanDto updateSeats(DriverPlanUpdateSeatsDto dto);
    List<DriverPlanDto> findAllByDriverId(Long id);
    DriverPlanDto delete(DriverPlanDeleteDto driverPlanDeleteDto, Long id) throws DriverMatchException;
}
