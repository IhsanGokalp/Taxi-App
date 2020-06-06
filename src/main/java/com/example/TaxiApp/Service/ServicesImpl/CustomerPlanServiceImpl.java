package com.example.TaxiApp.Service.ServicesImpl;


import com.example.TaxiApp.Entity.Customer;
import com.example.TaxiApp.Entity.Order.CustomerPlan;
import com.example.TaxiApp.Repository.CustomerPlanRepository;
import com.example.TaxiApp.Service.CustomerPlanService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerPlanServiceImpl implements CustomerPlanService {

    private final CustomerPlanRepository customerPlanRepository;


    public CustomerPlanServiceImpl(CustomerPlanRepository customerPlanRepository) {
        this.customerPlanRepository = customerPlanRepository;
    }

    @Override
    public CustomerPlan save(CustomerPlan customerPlan) {
        CustomerPlan saved = customerPlanRepository.save(customerPlan);
        return saved;
    }

    @Override
    public List<CustomerPlan> findAllByCustomerId(Customer customer) {
        return customerPlanRepository.findAllByCustomer(customer);
    }

    @Override
    public CustomerPlan delete(Long customerPlanId) {
        CustomerPlan delete = customerPlanRepository.findById(customerPlanId).
                orElseThrow(()->new EntityNotFoundException("There is no customer plan with id " +customerPlanId +"."));
        customerPlanRepository.delete(delete);
        return delete;
    }

    @Override
    public CustomerPlan findById(Long customerPlanId) {
        return customerPlanRepository.findById(customerPlanId).
                orElseThrow(()->new EntityNotFoundException("There is no customer plan with id " +
                        customerPlanId +"."));
    }
}
