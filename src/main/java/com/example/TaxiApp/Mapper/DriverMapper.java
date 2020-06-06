package com.example.TaxiApp.Mapper;

import com.example.TaxiApp.DTO.Driver.DriverCreateDto;
import com.example.TaxiApp.DTO.Driver.DriverDto;
import com.example.TaxiApp.DTO.Driver.DriverWithImageSavedDto;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.User;

public interface DriverMapper {
    Driver toDriver(DriverCreateDto createDto, User toBeInitialized);
    DriverDto toDto(Driver save);
    DriverWithImageSavedDto toDriverWithImage(Driver driver, boolean b);
}
