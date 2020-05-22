package com.example.TaxiApp.Controller;

import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanCreateDto;
import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanSavedDto;
import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanSearchDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.error.ValidationExceptionDto;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.Order.CustomerPlan;
import com.example.TaxiApp.Entity.Order.DriverPlan;
import com.example.TaxiApp.Mapper.CustomerPlanMapper;
import com.example.TaxiApp.Mapper.DriverMapper;
import com.example.TaxiApp.Mapper.DriverPlansMapper;
import com.example.TaxiApp.Service.CustomerPlanService;
import com.example.TaxiApp.Service.DriverPlansService;
import com.example.TaxiApp.endpoint.CustomerPlansEndpoint;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerPlans/{id}")
public class CustomerPlanController {

    private final CustomerPlanService customerPlanService;
    private final CustomerPlanMapper customerPlanMapper;
    private final DriverPlansService driverPlansService;
    private final DriverPlansMapper driverPlansMapper;
    private final DriverMapper driverMapper;
    private final CustomerPlansEndpoint customerPlansEndpoint;

    public CustomerPlanController(CustomerPlanService customerPlanService, CustomerPlanMapper customerPlanMapper,
                                  DriverPlansService driverPlansService1, DriverPlansMapper driverPlansMapper,
                                  DriverMapper driverMapper, CustomerPlansEndpoint customerPlansEndpoint) {
        this.customerPlanService = customerPlanService;
        this.customerPlanMapper = customerPlanMapper;
        this.driverPlansService = driverPlansService1;
        this.driverPlansMapper = driverPlansMapper;
        this.driverMapper = driverMapper;
        this.customerPlansEndpoint = customerPlansEndpoint;
    }

    @PostMapping("/search")
    private Page<DriverPlanDto> search(@RequestBody CustomerPlanSearchDto customerPlanCreateDto) {
        Page<DriverPlanDto> result = customerPlansEndpoint.search(customerPlanCreateDto);
        return result;
    }

    @PostMapping
    private CustomerPlanSavedDto save(@RequestBody CustomerPlanCreateDto dto,
                                      @PathVariable Long id) throws ValidationExceptionDto {
        CustomerPlan save = customerPlanService.save(dto, id);
        Driver driver = driverPlansService.findById(dto.getDriverPlanId()).getDriver();
        return customerPlanMapper.toCustomerPlanSavedDto(customerPlanMapper.
                        toCustomerPlanDto(save), driverMapper.toDto(driver),save.getId());
    }
}
