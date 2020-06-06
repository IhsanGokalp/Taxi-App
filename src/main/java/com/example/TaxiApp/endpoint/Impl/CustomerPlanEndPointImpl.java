package com.example.TaxiApp.endpoint.Impl;

import com.example.TaxiApp.DTO.CustomerPlan.*;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanUpdateSeatsDto;
import com.example.TaxiApp.DTO.error.CustomerMatchException;
import com.example.TaxiApp.DTO.error.ValidationExceptionDto;
import com.example.TaxiApp.Entity.Order.CustomerPlan;
import com.example.TaxiApp.Mapper.CustomerPlanMapper;
import com.example.TaxiApp.Mapper.DriverPlansMapper;
import com.example.TaxiApp.Service.CustomerPlanService;
import com.example.TaxiApp.Service.CustomerService;
import com.example.TaxiApp.Service.DriverPlansService;
import com.example.TaxiApp.endpoint.CustomerPlansEndpoint;
import com.example.TaxiApp.endpoint.DriverPlanEndpoint;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerPlanEndPointImpl implements CustomerPlansEndpoint {

    private final DriverPlansService driverPlansService;
    private final DriverPlansMapper driverPlansMapper;
    private final DriverPlanEndpoint driverPlanEndpoint;
    private final CustomerPlanMapper customerPlanMapper;
    private final CustomerPlanService customerPlanService;
    private final CustomerService customerService;

    public CustomerPlanEndPointImpl(DriverPlansService driverPlansService, DriverPlansMapper driverPlansMapper,
                                    DriverPlanEndpoint driverPlanEndpoint, CustomerPlanMapper customerPlanMapper,
                                    CustomerPlanService customerPlanService, CustomerService customerService) {
        this.driverPlansService = driverPlansService;
        this.driverPlansMapper = driverPlansMapper;
        this.driverPlanEndpoint = driverPlanEndpoint;
        this.customerPlanMapper = customerPlanMapper;
        this.customerPlanService = customerPlanService;
        this.customerService = customerService;
    }

    @Override
    public Page<DriverPlanDto> search(CustomerPlanSearchDto customerPlanCreateDto) {
        Page<DriverPlanDto> driverPlans = driverPlansService.
                getCloseFromLocations(customerPlanCreateDto).
                map(x->driverPlansMapper.toDriverPlanDto(x));

        return driverPlans;
    }

    @Override
    public CustomerPlanSavedDto save(CustomerPlanCreateDto dto, Long id)
            throws ValidationExceptionDto {
        DriverPlanDto driverPlanDto = driverPlanEndpoint.findById(dto.getDriverPlanId());
        if (dto.getNumOfPassengers() > driverPlanDto.getAvailableSeats()) {
            throw new ValidationExceptionDto(
                    dto.getDriverPlanId(),
                    driverPlanDto.getDriver(),
                    driverPlanDto.getAvailableSeats());
        }

        DriverPlanDto driverPlan = driverPlanEndpoint.updateSeats(
                new DriverPlanUpdateSeatsDto(driverPlanDto.getDriverPlanId(),
                        dto.getNumOfPassengers()));

        CustomerPlan customerPlan = customerPlanMapper.toCustomerPlan(dto, id);
        CustomerPlan save = customerPlanService.save(customerPlan);

        return customerPlanMapper.toCustomerPlanSavedDto(save,
                driverPlan.getDriver());
    }

    @Override
    public List<CustomerPlanDto> findAllByCustomerId(Long id) {
        return customerPlanService.findAllByCustomerId(customerService.findById(id)).stream().
                map(customerPlanMapper::toCustomerPlanDto).collect(Collectors.toList());
    }

    @Override
    public CustomerPlanDto delete(Long id, CustomerPlanDeleteDto customerPlanDeleteDto)
            throws CustomerMatchException {
        CustomerPlan plan = customerPlanService.
                findById(customerPlanDeleteDto.getCustomerPlanId());
        if (plan.getCustomer().getId() != id)
            throw new CustomerMatchException(id,customerPlanDeleteDto.getCustomerPlanId());
        driverPlansService.updateSeats(new DriverPlanUpdateSeatsDto(
                plan.getDriverPlan().getId(), plan.getNumOfPassengers()*-1));
        return customerPlanMapper.toCustomerPlanDto(customerPlanService.
                delete(customerPlanDeleteDto.getCustomerPlanId()));
    }
}
