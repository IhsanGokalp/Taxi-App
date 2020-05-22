package com.example.TaxiApp.Service;

import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanCreateDto;
import com.example.TaxiApp.DTO.error.ValidationExceptionDto;
import com.example.TaxiApp.Entity.Order.CustomerPlan;

public interface CustomerPlanService {
    CustomerPlan save(CustomerPlan customerPlanCreateDto);
}
