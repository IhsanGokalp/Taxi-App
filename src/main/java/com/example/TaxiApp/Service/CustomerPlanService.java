package com.example.TaxiApp.Service;


import com.example.TaxiApp.Entity.Customer;
import com.example.TaxiApp.Entity.Order.CustomerPlan;

import java.util.List;

public interface CustomerPlanService {
    CustomerPlan save(CustomerPlan customerPlanCreateDto);
    List<CustomerPlan> findAllByCustomerId(Customer customer);
    CustomerPlan delete(Long customerPlanId);
    CustomerPlan findById(Long customerPlanId);
}
