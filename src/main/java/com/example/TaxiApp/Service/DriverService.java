package com.example.TaxiApp.Service;

import com.example.TaxiApp.DTO.Driver.DriverDto;
import com.example.TaxiApp.DTO.Driver.DriverWithImageSavedDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanUpdateSeatsDto;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.Order.DriverPlan;
import org.springframework.web.multipart.MultipartFile;

public interface DriverService {
    Driver save(Driver driver);
    DriverWithImageSavedDto saveImage(MultipartFile imageSendDto, Long id);
    Driver findById(Long id);
    DriverDto search(Long driverPlanId);


}
