package com.example.TaxiApp.Mapper;

import com.example.TaxiApp.DTO.Driver.DriverCreateDto;
import com.example.TaxiApp.DTO.Driver.DriverDto;
import com.example.TaxiApp.DTO.Driver.DriverWithImageSavedDto;
import com.example.TaxiApp.Entity.Driver;

public interface DriverMapper {
    Driver toDriver(DriverCreateDto createDto);
    DriverDto toDto(Driver save);
    DriverWithImageSavedDto toDriverWithImage(Driver driver, boolean b);
}
