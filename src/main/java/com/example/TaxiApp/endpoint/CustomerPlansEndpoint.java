package com.example.TaxiApp.endpoint;

import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanCreateDto;
import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanSavedDto;
import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanSearchDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.error.ValidationExceptionDto;
import org.springframework.data.domain.Page;

public interface CustomerPlansEndpoint {
    Page<DriverPlanDto> search(CustomerPlanSearchDto customerPlanCreateDto);
    CustomerPlanSavedDto save(CustomerPlanCreateDto dto, Long id) throws ValidationExceptionDto;
}
