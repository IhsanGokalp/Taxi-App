package com.example.TaxiApp.Controller;

import com.example.TaxiApp.DTO.Customer.CustomerCreateDto;
import com.example.TaxiApp.DTO.Customer.CustomerDto;
import com.example.TaxiApp.DTO.Customer.CustomerWithImageSavedDto;
import com.example.TaxiApp.DTO.error.UserRoleRegisteredException;
import com.example.TaxiApp.Entity.Customer;
import com.example.TaxiApp.Entity.User;
import com.example.TaxiApp.Mapper.CustomerMapper;
import com.example.TaxiApp.Service.CustomerService;
import com.example.TaxiApp.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final PasswordEncoder passwordEncoder;
    private CustomerService customerService;
    private CustomerMapper customerMapper;
    private final UserService userService;

    public CustomerController(PasswordEncoder passwordEncoder, CustomerService customerService,
                              CustomerMapper customerMapper, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.userService = userService;
    }

    @PostMapping("/newCustomer")
    private CustomerDto save(@RequestBody CustomerCreateDto customerCreateDto)
            throws UserRoleRegisteredException {

        User toBeInitialized = userService.
                findByUsername(customerCreateDto.getFirstName()+customerCreateDto.getSurname());
        User user_id;
        if (toBeInitialized == null) {
            User user = new User();
            user.setPassword(passwordEncoder.encode(customerCreateDto.getPhoneNumber()));
            user.setRoles("CUSTOMER");
            user.setUsername(customerCreateDto.getFirstName() + customerCreateDto.getSurname());
            userService.save(user);
            user_id = user;
        }

        else {
            List<String> authorities =
                    Arrays.asList(toBeInitialized.getRoles().split(","));
            if (authorities.contains("CUSTOMER"))
                throw new UserRoleRegisteredException("This user already has this role.");
            else {
                toBeInitialized.setRoles(toBeInitialized.getRoles()+",CUSTOMER");
                userService.save(toBeInitialized);
            }
            user_id = toBeInitialized;
        }
        Customer customer = customerMapper.toCustomer(customerCreateDto, user_id);
        return customerService.save(customer);
    }

    @PutMapping("/{id}/imageSave")
    private CustomerWithImageSavedDto saveImage(@RequestParam("image")MultipartFile imageSendDto,
                                                @PathVariable Long id) {
        return customerService.saveImage(imageSendDto, id);
    }
}
