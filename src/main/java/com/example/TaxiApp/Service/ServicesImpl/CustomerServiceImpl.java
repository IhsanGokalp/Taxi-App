package com.example.TaxiApp.Service.ServicesImpl;

import com.example.TaxiApp.DTO.Customer.CustomerDto;
import com.example.TaxiApp.DTO.Customer.CustomerWithImageSavedDto;
import com.example.TaxiApp.Entity.Customer;
import com.example.TaxiApp.Mapper.CustomerMapper;
import com.example.TaxiApp.Repository.CustomerRepository;
import com.example.TaxiApp.Service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto save(Customer customer) {
        return customerMapper.toCustomerDto(customerRepository.save(customer));
    }

    @Override
    public CustomerWithImageSavedDto saveImage(MultipartFile imageSendDto, Long id) {
        Customer customer = findById(id);
        try {
            customer.setPhoto(imageSendDto.getBytes());
            Customer savedCustomer = customerRepository.save(customer);
            return customerMapper.toCustomerWithImage(savedCustomer,true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerMapper.toCustomerWithImage(customer,false);
    }

    @Override
    public Customer findById(Long id) {
        Customer customer = customerRepository.findById(id).
                orElseThrow(()-> new EntityNotFoundException("There is no customer with id "+ id));
        return customer;
    }
}
