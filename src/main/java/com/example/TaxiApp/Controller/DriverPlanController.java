package com.example.TaxiApp.Controller;

import com.example.TaxiApp.DTO.DriverPlan.DriverPlanCreateDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDeleteDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.error.DriverMatchException;
import com.example.TaxiApp.Service.DriverPlansService;
import com.example.TaxiApp.endpoint.DriverPlanEndpoint;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/driverPlans/{id}")
public class DriverPlanController {

    private final DriverPlanEndpoint driverPlanEndpoint;

    public DriverPlanController(DriverPlanEndpoint driverPlanEndpoint) {
        this.driverPlanEndpoint = driverPlanEndpoint;
    }

    @GetMapping
    private List<DriverPlanDto> getDriverPlans(@PathVariable Long id) {
        return driverPlanEndpoint.findAllByDriverId(id);
    }

    @PostMapping
    private DriverPlanDto save(@PathVariable Long id, @RequestBody DriverPlanCreateDto dto)
            throws DriverMatchException {
        return driverPlanEndpoint.save(dto,id);
    }

    @DeleteMapping
    private DriverPlanDto delete(@PathVariable Long id,
                                 @RequestBody DriverPlanDeleteDto driverPlanDeleteDto)
            throws DriverMatchException {
        return driverPlanEndpoint.delete(driverPlanDeleteDto, id);
    }
}
