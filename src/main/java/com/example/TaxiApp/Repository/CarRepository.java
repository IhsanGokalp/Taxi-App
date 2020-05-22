package com.example.TaxiApp.Repository;

import com.example.TaxiApp.Entity.Car;
import com.example.TaxiApp.Entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    ArrayList<Car> findAllByDriver(Driver id);
}
