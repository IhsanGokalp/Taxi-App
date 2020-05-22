package com.example.TaxiApp.endpoint.Impl;

import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanCreateDto;
import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanSavedDto;
import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanSearchDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanUpdateSeatsDto;
import com.example.TaxiApp.DTO.error.ValidationExceptionDto;
import com.example.TaxiApp.Entity.Order.CustomerPlan;
import com.example.TaxiApp.Mapper.CustomerPlanMapper;
import com.example.TaxiApp.Mapper.DriverPlansMapper;
import com.example.TaxiApp.Service.CustomerPlanService;
import com.example.TaxiApp.Service.DriverPlansService;
import com.example.TaxiApp.endpoint.CustomerPlansEndpoint;
import com.example.TaxiApp.endpoint.DriverPlanEndpoint;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CustomerPlanEndPointImpl implements CustomerPlansEndpoint {
    private final DriverPlansService driverPlansService;
    private final DriverPlansMapper driverPlansMapper;
    private final DriverPlanEndpoint driverPlanEndpoint;
    private final CustomerPlanMapper customerPlanMapper;
    private final CustomerPlanService customerPlanService;
    public CustomerPlanEndPointImpl(DriverPlansService driverPlansService, DriverPlansMapper driverPlansMapper,
                                    DriverPlanEndpoint driverPlanEndpoint, CustomerPlanMapper customerPlanMapper, CustomerPlanService customerPlanService) {
        this.driverPlansService = driverPlansService;
        this.driverPlansMapper = driverPlansMapper;
        this.driverPlanEndpoint = driverPlanEndpoint;
        this.customerPlanMapper = customerPlanMapper;
        this.customerPlanService = customerPlanService;
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
        int availableSeats = driverPlansService.
                findById(dto.getDriverPlanId()).getAvailableSeats();

        DriverPlanDto driverPlanDto = driverPlanEndpoint.findById(id);
        if (dto.getNumOfPassengers() > availableSeats) {
            throw new ValidationExceptionDto(
                    dto.getDriverPlanId(),
                    driverPlanDto.getDriver(),
                    availableSeats);
        }

        DriverPlanDto driverPlan = driverPlanEndpoint.updateSeats(
                new DriverPlanUpdateSeatsDto(driverPlanDto.getDriverPlanId(),
                        driverPlanDto.getAvailableSeats()));
        CustomerPlan customerPlan = customerPlanMapper.toCustomerPlan(dto, id);
        CustomerPlan save = customerPlanService.save(customerPlan);
        return customerPlanMapper.toCustomerPlanSavedDto(customerPlanMapper.toCustomerPlanDto(save),
                driverPlanDto.getDriver(),save.getId());
    }
}
