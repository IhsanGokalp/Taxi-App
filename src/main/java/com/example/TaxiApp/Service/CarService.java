package com.example.TaxiApp.Service;

import com.example.TaxiApp.DTO.Car.CarDto;
import com.example.TaxiApp.Entity.Car;
import com.example.TaxiApp.Entity.Driver;

import java.util.ArrayList;

public interface CarService {
    CarDto save(Car toCar);
    ArrayList<Car> findAllCarsOfDriver(Driver driverId);
}
