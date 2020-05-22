package com.example.TaxiApp.Service;

import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanCreateDto;
import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanSearchDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanCreateDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanUpdateSeatsDto;
import com.example.TaxiApp.Entity.Order.DriverPlan;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

public interface DriverPlansService {
    DriverPlan save(DriverPlan dto);
    Page<DriverPlan> getCloseFromLocations(CustomerPlanSearchDto customerPlanSearchDto);
    DriverPlan findById(Long driverPlanId);
    DriverPlan updateSeats(DriverPlanUpdateSeatsDto dto);

}
