package com.example.TaxiApp.Service.ServicesImpl;

import com.example.TaxiApp.DTO.Driver.DriverDto;
import com.example.TaxiApp.DTO.Driver.DriverWithImageSavedDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanUpdateSeatsDto;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.Order.DriverPlan;
import com.example.TaxiApp.Mapper.DriverMapper;
import com.example.TaxiApp.Repository.DriverRepository;
import com.example.TaxiApp.Service.DriverPlansService;
import com.example.TaxiApp.Service.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
public class DriverServiceImpl implements DriverService {

    private DriverRepository driverRepository;
    private DriverMapper driverMapper;

    public DriverServiceImpl(DriverRepository driverRepository, DriverMapper driverMapper) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
    }

    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public DriverWithImageSavedDto saveImage(MultipartFile imageSendDto, Long id) {
        Driver driver = driverRepository.findById(id).
                orElseThrow(()->new EntityNotFoundException("There is no driver with id " + id));
        try {
            driver.setPhoto(imageSendDto.getBytes());
            Driver saved = driverRepository.save(driver);
            return driverMapper.toDriverWithImage(saved,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driverMapper.toDriverWithImage(driver,false);
    }

    @Override
    public Driver findById(Long id) {
        return driverRepository.findById(id).
                orElseThrow(()->new EntityNotFoundException("There is no driver with id "+ id));
    }

    @Override
    public DriverDto search(Long driverPlanId) {
        Driver driver = driverRepository.findById(driverPlanId).
                orElseThrow(()->new EntityNotFoundException("There is no driver with id "+ driverPlanId));
        return driverMapper.toDto(driver);
    }


}
