package com.example.TaxiApp.Mapper;

import com.example.TaxiApp.DTO.Customer.CustomerCreateDto;
import com.example.TaxiApp.DTO.Customer.CustomerDto;
import com.example.TaxiApp.DTO.Customer.CustomerWithImageSavedDto;
import com.example.TaxiApp.Entity.Customer;
import org.springframework.stereotype.Service;


@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerCreateDto createDto) {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(createDto.getFirstName());
        newCustomer.setPhoneNumber(createDto.getPhoneNumber());
        newCustomer.setSurname(createDto.getSurname());
        return newCustomer;
    }
    public CustomerDto toCustomerDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setFirstName(customer.getFirstName());
        dto.setId(customer.getId());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setSurname(customer.getSurname());
        return dto;
    }

    public CustomerWithImageSavedDto toCustomerWithImage(Customer customer, boolean saved) {
        CustomerWithImageSavedDto dto = new CustomerWithImageSavedDto();
        dto.setFirstName(customer.getFirstName());
        dto.setId(customer.getId());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setSurname(customer.getSurname());
        dto.setImageSaved(saved);
        return dto;
    }
}
