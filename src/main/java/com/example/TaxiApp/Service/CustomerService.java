package com.example.TaxiApp.Service;

import com.example.TaxiApp.DTO.Customer.CustomerDto;
import com.example.TaxiApp.DTO.Customer.CustomerWithImageSavedDto;
import com.example.TaxiApp.Entity.Customer;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerService {
    CustomerDto save(Customer customer);
    CustomerWithImageSavedDto saveImage(MultipartFile imageSendDto, Long id);
    Customer findById(Long id);
}
