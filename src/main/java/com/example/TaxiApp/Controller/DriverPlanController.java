package com.example.TaxiApp.Controller;

import com.example.TaxiApp.DTO.DriverPlan.DriverPlanCreateDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.Service.DriverPlansService;
import com.example.TaxiApp.endpoint.DriverPlanEndpoint;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driverPlans/{id}")
public class DriverPlanController {

    private final DriverPlansService driverPlansService;
    private final DriverPlanEndpoint driverPlanEndpoint;

    public DriverPlanController(DriverPlansService driverPlansService, DriverPlanEndpoint driverPlanEndpoint) {
        this.driverPlansService = driverPlansService;
        this.driverPlanEndpoint = driverPlanEndpoint;
    }

    @PostMapping
    private DriverPlanDto save(@PathVariable Long id, @RequestBody DriverPlanCreateDto dto) {
        return driverPlanEndpoint.save(dto,id);
    }
}
