package com.example.TaxiApp.Controller;

import com.example.TaxiApp.DTO.CustomerPlan.*;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.error.CustomerMatchException;
import com.example.TaxiApp.DTO.error.ValidationExceptionDto;
import com.example.TaxiApp.Entity.Driver;
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

    private final CustomerPlansEndpoint customerPlansEndpoint;

    public CustomerPlanController(CustomerPlansEndpoint customerPlansEndpoint) {

        this.customerPlansEndpoint = customerPlansEndpoint;
    }

    @GetMapping
    private List<CustomerPlanDto> findAllById(@PathVariable Long id) {
        return customerPlansEndpoint.findAllByCustomerId(id);
    }

    @PostMapping("/search")
    private Page<DriverPlanDto> search(@RequestBody CustomerPlanSearchDto customerPlanCreateDto) {
        Page<DriverPlanDto> result = customerPlansEndpoint.search(customerPlanCreateDto);
        return result;
    }

    @PostMapping
    private CustomerPlanSavedDto save(@RequestBody CustomerPlanCreateDto dto,
                                      @PathVariable Long id) throws ValidationExceptionDto {
        CustomerPlanSavedDto save = customerPlansEndpoint.save(dto, id);
        return save;
    }

    @DeleteMapping
    private CustomerPlanDto delete(@RequestBody CustomerPlanDeleteDto customerPlanDeleteDto,
                                   @PathVariable Long id) throws CustomerMatchException {
        return customerPlansEndpoint.delete(id, customerPlanDeleteDto);
    }

}
