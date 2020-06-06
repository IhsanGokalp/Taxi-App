package com.example.TaxiApp.Repository;

import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.Order.DriverPlan;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverPlanRepository extends JpaRepository<DriverPlan, Long>,
        QuerydslPredicateExecutor<DriverPlan> {
    List<DriverPlan> findAllByFromLatBetweenAndFromLonBetween(double minLat, double maxLat,
                                                              double minLon, double maxLon);
    List<DriverPlan> findAllByToLatBetweenAndToLonBetween(double minLat, double maxLat,
                                                          double minLon, double maxLon);
    List<DriverPlan> findAllById(Long id);
    List<DriverPlan> findAllByDriver(Driver driverId);
}
