package com.example.TaxiApp.Controller;

import com.example.TaxiApp.DTO.Driver.DriverCreateDto;
import com.example.TaxiApp.DTO.Driver.DriverDto;
import com.example.TaxiApp.DTO.Driver.DriverWithImageSavedDto;

import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Mapper.CarMapper;
import com.example.TaxiApp.Mapper.DriverMapper;
import com.example.TaxiApp.Mapper.Impl.DriverMapperImpl;
import com.example.TaxiApp.Service.CarService;
import com.example.TaxiApp.Service.DriverService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@RestController()
@RequestMapping("/drivers")
public class DriverController {
    private DriverService driverService;
    private DriverMapper driverMapper;
    private CarMapper carMapper;
    private CarService carService;

    public DriverController(DriverService driverService, DriverMapper driverMapper,
                            CarMapper carMapper, CarService carService) {
        this.driverService = driverService;
        this.driverMapper = driverMapper;
        this.carMapper = carMapper;
        this.carService = carService;
    }

    @PostMapping
    private DriverDto save(@RequestBody DriverCreateDto createDto) {
        System.out.println("In");
        Driver driver = driverMapper.toDriver(createDto);
        Driver saved = driverService.save(driver);
        createDto.getCars().
                stream().
                map(x->carMapper.toCar(x,driver)).
                collect(Collectors.toList()).
                forEach(car -> carService.save(car));
        return driverMapper.toDto(saved);
    }

    @PutMapping("/{id}/imageSave")
    private DriverWithImageSavedDto saveImage(@RequestParam("image") MultipartFile file,
                                              @PathVariable Long id) {
        return driverService.saveImage(file, id);
    }
}
