package com.example.TaxiApp.Service;

import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanSearchDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanUpdateSeatsDto;
import com.example.TaxiApp.DTO.error.DriverMatchException;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.Order.DriverPlan;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DriverPlansService {
    DriverPlan save(DriverPlan dto);
    Page<DriverPlan> getCloseFromLocations(CustomerPlanSearchDto customerPlanSearchDto);
    DriverPlan findById(Long driverPlanId);
    DriverPlan updateSeats(DriverPlanUpdateSeatsDto dto);
    List<DriverPlan> findAllByDriverId(Driver id);
    DriverPlan delete(Long driverPlanId, Long id) throws DriverMatchException;
}
