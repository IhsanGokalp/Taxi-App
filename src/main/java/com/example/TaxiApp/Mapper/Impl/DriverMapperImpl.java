package com.example.TaxiApp.Mapper.Impl;

import com.example.TaxiApp.DTO.Driver.DriverCreateDto;
import com.example.TaxiApp.DTO.Driver.DriverDto;
import com.example.TaxiApp.DTO.Driver.DriverWithImageSavedDto;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.User;
import com.example.TaxiApp.Mapper.CarMapper;
import com.example.TaxiApp.Mapper.DriverMapper;
import com.example.TaxiApp.Service.CarService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class DriverMapperImpl implements DriverMapper {

    private CarMapper carMapper;
    private CarService carService;

    public DriverMapperImpl(CarMapper carMapper, CarService carService) {
        this.carMapper = carMapper;
        this.carService = carService;
    }


    public Driver toDriver(DriverCreateDto createDto, User toBeInitialized) {
        Driver driver = new Driver();
        driver.setFirstName(createDto.getFirstName());
        driver.setPhoneNumber(createDto.getPhoneNumber());
        driver.setSurname(createDto.getSurname());
        driver.setUser(toBeInitialized);
        return driver;
    }

    public DriverDto toDto(Driver save) {
        DriverDto dto = new DriverDto();
        dto.setId(save.getId());
        dto.setFirstName(save.getFirstName());
        dto.setSurname(save.getSurname());
        dto.setPhoneNumber(save.getPhoneNumber());
        dto.setCars(carService.findAllCarsOfDriver(save).
                stream().
                map(carMapper::toCarDto).
                collect(Collectors.toList()));
        return dto;
    }

    public DriverWithImageSavedDto toDriverWithImage(Driver driver, boolean b) {
        DriverWithImageSavedDto dto = new DriverWithImageSavedDto();
        dto.setId(driver.getId());
        dto.setFirstName(driver.getFirstName());
        dto.setSurname(driver.getSurname());
        dto.setPhoneNumber(driver.getPhoneNumber());
        dto.setImageSaved(b);
        return dto;
    }
}
