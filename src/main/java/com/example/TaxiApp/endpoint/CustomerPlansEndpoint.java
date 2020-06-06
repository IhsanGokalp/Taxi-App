package com.example.TaxiApp.endpoint;

import com.example.TaxiApp.DTO.CustomerPlan.*;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.error.CustomerMatchException;
import com.example.TaxiApp.DTO.error.ValidationExceptionDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerPlansEndpoint {
    Page<DriverPlanDto> search(CustomerPlanSearchDto customerPlanCreateDto);
    CustomerPlanSavedDto save(CustomerPlanCreateDto dto, Long id) throws ValidationExceptionDto;
    List<CustomerPlanDto> findAllByCustomerId(Long id);
    CustomerPlanDto delete(Long id, CustomerPlanDeleteDto customerPlanDeleteDto) throws CustomerMatchException;
}
