package com.example.TaxiApp.Mapper;

import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanCreateDto;
import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanDto;
import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanSavedDto;
import com.example.TaxiApp.DTO.Driver.DriverDto;
import com.example.TaxiApp.Entity.Order.CustomerPlan;

public interface CustomerPlanMapper {
    CustomerPlan toCustomerPlan(CustomerPlanCreateDto dto, Long id);

    CustomerPlanDto toCustomerPlanDto(CustomerPlan saved);

    CustomerPlanSavedDto toCustomerPlanSavedDto(CustomerPlanDto save,
                                                DriverDto driverDto, Long id);
}
