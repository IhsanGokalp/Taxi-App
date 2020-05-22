package com.example.TaxiApp.Service.ServicesImpl;

import com.example.TaxiApp.DTO.Car.CarDto;
import com.example.TaxiApp.Entity.Car;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Mapper.CarMapper;
import com.example.TaxiApp.Repository.CarRepository;
import com.example.TaxiApp.Service.CarService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CarServiceImpl implements CarService {

    private CarMapper carMapper;
    private CarRepository carRepository;

    public CarServiceImpl(CarMapper carMapper, CarRepository carRepository) {
        this.carMapper = carMapper;
        this.carRepository = carRepository;
    }

    @Override
    public CarDto save(Car toCar) {
        return carMapper.toCarDto(carRepository.save(toCar));
    }

    @Override
    public ArrayList<Car> findAllCarsOfDriver(Driver driverId) {
        return carRepository.findAllByDriver(driverId);
    }
}
