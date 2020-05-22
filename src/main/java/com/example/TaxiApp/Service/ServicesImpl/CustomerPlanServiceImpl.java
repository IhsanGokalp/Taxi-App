package com.example.TaxiApp.Service.ServicesImpl;

import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanCreateDto;
import com.example.TaxiApp.DTO.Driver.DriverDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanUpdateSeatsDto;
import com.example.TaxiApp.DTO.error.ValidationExceptionDto;
import com.example.TaxiApp.Entity.Order.CustomerPlan;
import com.example.TaxiApp.Mapper.CustomerPlanMapper;
import com.example.TaxiApp.Mapper.DriverMapper;
import com.example.TaxiApp.Repository.CustomerPlanRepository;
import com.example.TaxiApp.Service.CustomerPlanService;
import com.example.TaxiApp.Service.DriverPlansService;
import com.example.TaxiApp.endpoint.DriverPlanEndpoint;
import org.springframework.stereotype.Service;

@Service
public class CustomerPlanServiceImpl implements CustomerPlanService {

    private final CustomerPlanMapper customerPlanMapper;
    private final CustomerPlanRepository customerPlanRepository;
    private final DriverPlansService driverPlansService;
    private final DriverMapper driverMapper;
    private final DriverPlanEndpoint driverPlanEndpoint;

    public CustomerPlanServiceImpl(CustomerPlanMapper customerPlanMapper, CustomerPlanRepository customerPlanRepository,
                                   DriverPlansService driverPlansService, DriverMapper driverMapper, DriverPlanEndpoint driverPlanEndpoint) {
        this.customerPlanMapper = customerPlanMapper;
        this.customerPlanRepository = customerPlanRepository;
        this.driverPlansService = driverPlansService;
        this.driverMapper = driverMapper;
        this.driverPlanEndpoint = driverPlanEndpoint;
    }

    @Override
    public CustomerPlan save(CustomerPlan customerPlan) {
        CustomerPlan saved = customerPlanRepository.save(customerPlan);
        return saved;
    }
}
