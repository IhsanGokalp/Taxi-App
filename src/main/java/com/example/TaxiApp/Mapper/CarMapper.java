package com.example.TaxiApp.Mapper;

import com.example.TaxiApp.DTO.Car.CarCreateDto;
import com.example.TaxiApp.DTO.Car.CarDto;
import com.example.TaxiApp.Entity.Car;
import com.example.TaxiApp.Entity.Driver;
import org.springframework.stereotype.Service;

@Service
public class CarMapper {

    public Car toCar(CarCreateDto x, Driver id) {
        Car newCar = new Car();
        newCar.setColor(x.getColor());
        newCar.setCompany(x.getCompany());
        newCar.setDriver(id);
        newCar.setLicencePlate(x.getLicencePlate());
        newCar.setModel(x.getModel());
        newCar.setVariationOfColor(x.getVariationOfColor());
        return newCar;
    }

    public CarDto toCarDto(Car save) {
        CarDto dto = new CarDto();
        dto.setColor(save.getColor());
        dto.setCompany(save.getCompany());
        dto.setId(save.getId());
        dto.setLicencePlate(save.getLicencePlate());
        dto.setModel(save.getModel());
        dto.setVariationOfColor(save.getVariationOfColor());
        return dto;
    }
}
