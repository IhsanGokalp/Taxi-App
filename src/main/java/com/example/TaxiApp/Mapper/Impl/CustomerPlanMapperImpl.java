package com.example.TaxiApp.Mapper.Impl;

import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanCreateDto;
import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanDto;
import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanSavedDto;
import com.example.TaxiApp.DTO.Driver.DriverDto;
import com.example.TaxiApp.Entity.Order.CustomerPlan;
import com.example.TaxiApp.Mapper.CustomerMapper;
import com.example.TaxiApp.Mapper.CustomerPlanMapper;
import com.example.TaxiApp.Service.CustomerService;
import com.example.TaxiApp.Service.DriverPlansService;
import org.springframework.stereotype.Service;

@Service
public class CustomerPlanMapperImpl implements CustomerPlanMapper {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final DriverPlansService driverPlansService;


    public CustomerPlanMapperImpl(CustomerService customerService, CustomerMapper customerMapper, DriverPlansService driverPlansService) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.driverPlansService = driverPlansService;
    }

    @Override
    public CustomerPlan toCustomerPlan(CustomerPlanCreateDto dto, Long id) {
        CustomerPlan ans = new CustomerPlan();
        ans.setDriverPlan(driverPlansService.findById(dto.getDriverPlanId()));
        ans.setCustomer(customerService.findById(id));
        ans.setFromLat(dto.getFromLat());
        ans.setFromLon(dto.getFromLon());
        ans.setNumOfPassengers(dto.getNumOfPassengers());
        ans.setPassengerType(dto.getPassengerType());
        ans.setTime(dto.getTime());
        ans.setToLat(dto.getToLat());
        ans.setToLon(dto.getToLon());
        return ans;
    }

    @Override
    public CustomerPlanDto toCustomerPlanDto(CustomerPlan customerPlan) {
        CustomerPlanDto ans = new CustomerPlanDto();
        ans.setCustomerDto(customerMapper.toCustomerDto(customerPlan.getCustomer()));
        ans.setFromLat(customerPlan.getFromLat());
        ans.setFromLon(customerPlan.getFromLon());
        ans.setNumOfPassengers(customerPlan.getNumOfPassengers());
        ans.setPassengerType(ans.getPassengerType());
        ans.setTime(customerPlan.getTime());
        ans.setToLat(customerPlan.getToLat());
        ans.setToLon(customerPlan.getToLon());
        ans.setId(customerPlan.getId());
        return ans;
    }

    @Override
    public CustomerPlanSavedDto toCustomerPlanSavedDto(CustomerPlan save, DriverDto driverDto) {
        CustomerPlanSavedDto savedDto = new CustomerPlanSavedDto();
        savedDto.setCustomerId(save.getCustomer().getId());
        savedDto.setFromLat(save.getFromLat());
        savedDto.setFromLon(save.getFromLon());
        savedDto.setToLat(save.getToLat());
        savedDto.setToLon(save.getToLon());
        savedDto.setNumOfPassengers(save.getNumOfPassengers());
        savedDto.setDriverDto(driverDto);
        savedDto.setCustomerPlanId(save.getId());
        return savedDto;
    }
}
