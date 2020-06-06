package com.example.TaxiApp.Controller;

import com.example.TaxiApp.DTO.Driver.DriverCreateDto;
import com.example.TaxiApp.DTO.Driver.DriverDto;
import com.example.TaxiApp.DTO.Driver.DriverWithImageSavedDto;

import com.example.TaxiApp.DTO.error.CarNotProvidedException;
import com.example.TaxiApp.DTO.error.UserRoleRegisteredException;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.User;
import com.example.TaxiApp.Mapper.CarMapper;
import com.example.TaxiApp.Mapper.DriverMapper;
import com.example.TaxiApp.Service.CarService;
import com.example.TaxiApp.Service.DriverService;
import com.example.TaxiApp.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/drivers")
public class DriverController {
    private DriverService driverService;
    private DriverMapper driverMapper;
    private CarMapper carMapper;
    private CarService carService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public DriverController(DriverService driverService, DriverMapper driverMapper,
                            CarMapper carMapper, CarService carService, UserService userService, PasswordEncoder passwordEncoder) {
        this.driverService = driverService;
        this.driverMapper = driverMapper;
        this.carMapper = carMapper;
        this.carService = carService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    private DriverDto save(@RequestBody DriverCreateDto createDto)
            throws UserRoleRegisteredException, CarNotProvidedException {
        User toBeInitialized = userService.
                findByUsername(createDto.getFirstName()+createDto.getSurname());
        User user;
        if (createDto.getCars() == null || createDto.getCars().isEmpty()) {
            throw new CarNotProvidedException("Driver cannot register without registering car.");
        }
        if (toBeInitialized == null) {
            user = new User();
            user.setRoles("DRIVER");
            user.setUsername(createDto.getFirstName()+createDto.getSurname());
            user.setPassword(passwordEncoder.encode(createDto.getPhoneNumber()));
            userService.save(user);
        }
        else {
            List<String> authorities =
                    Arrays.asList(toBeInitialized.getRoles().split(","));
            if (authorities.contains("DRIVER"))
                throw new UserRoleRegisteredException("This user already has this role.");
            else {
                toBeInitialized.setRoles(toBeInitialized.getRoles()+",DRIVER");
                userService.save(toBeInitialized);
                user = toBeInitialized;
            }
        }
        Driver driver = driverMapper.toDriver(createDto, user);
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
