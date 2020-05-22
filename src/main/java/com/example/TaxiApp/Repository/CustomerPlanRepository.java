package com.example.TaxiApp.Repository;

import com.example.TaxiApp.Entity.Order.CustomerPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPlanRepository extends JpaRepository<CustomerPlan, Long> {
}
