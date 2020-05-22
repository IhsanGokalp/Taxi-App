package com.example.TaxiApp.Controller;

import com.example.TaxiApp.DTO.Customer.CustomerCreateDto;
import com.example.TaxiApp.DTO.Customer.CustomerDto;
import com.example.TaxiApp.DTO.Customer.CustomerWithImageSavedDto;
import com.example.TaxiApp.Entity.Customer;
import com.example.TaxiApp.Mapper.CustomerMapper;
import com.example.TaxiApp.Service.CustomerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;
    private CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping("/newCustomer")
    private CustomerDto save(@RequestBody CustomerCreateDto customerCreateDto) {
        Customer customer = customerMapper.toCustomer(customerCreateDto);
        return customerService.save(customer);
    }

    @PutMapping("/{id}/imageSave")
    private CustomerWithImageSavedDto saveImage(@RequestParam("image")MultipartFile imageSendDto, @PathVariable Long id) {
        return customerService.saveImage(imageSendDto, id);
    }


}
