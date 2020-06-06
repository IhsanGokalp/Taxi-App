package com.example.TaxiApp.Repository;

import com.example.TaxiApp.Entity.Customer;
import com.example.TaxiApp.Entity.Order.CustomerPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerPlanRepository extends JpaRepository<CustomerPlan, Long> {
    List<CustomerPlan> findAllByCustomer(Customer customer);
}
