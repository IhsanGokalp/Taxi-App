package com.example.TaxiApp.Config;

import com.example.TaxiApp.DTO.error.*;
import com.example.TaxiApp.DTO.error.Response.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;


@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(ValidationExceptionDto.class)
    public ValidationExceptionResponse methodArgumentNotValidExceptionHandler(
            ValidationExceptionDto e) {
        ValidationExceptionResponse exceptionDto = new ValidationExceptionResponse();
        exceptionDto.setMessage("Maximum number of seats for driver plan "+ e.getDriverPlanId() +
                " with driver " + e.getDriverDto().getId() + " is " + e.getCarSits());
        exceptionDto.setCode(HttpStatus.EXPECTATION_FAILED.toString());
        return exceptionDto;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DriverMatchException.class)
    public DriverMatchResponse methodDriverDontMatchExceptionHandler(DriverMatchException e) {
        DriverMatchResponse response = new DriverMatchResponse();
        response.setCode(HttpStatus.BAD_REQUEST.toString());
        response.setMessage("The driver plan with id " +
                e.getDriverPlanId() + " does not belong to driver with id " + e.getGiveDriverId());
        return response;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public EntityNotFoundResponseDto methodEntityNotFoundException(EntityNotFoundException e) {
        EntityNotFoundResponseDto errorDto = new EntityNotFoundResponseDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setCode(HttpStatus.NOT_FOUND.toString());
        return errorDto;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerMatchException.class)
    public CustomerNotMatchResponse methodCustomerMatchExceptionHandler(CustomerMatchException e) {
        CustomerNotMatchResponse response = new CustomerNotMatchResponse();
        response.setCode(HttpStatus.BAD_REQUEST.toString());
        response.setMessage("The customer plan with id " +
                e.getCustomerPlanId() + " does not belong to customer with id " + e.getGivenCustomerId());
        return response;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserRoleRegisteredException.class)
    public UserRoleRegisteredResponse methodUserRoleRegisteredExceptionHandler(UserRoleRegisteredException e) {
        UserRoleRegisteredResponse registeredResponse = new UserRoleRegisteredResponse();
        registeredResponse.setMessage(e.getMessage());
        registeredResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        return registeredResponse;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CarNotProvidedException.class)
    public CarNotProvidedResponse methodCarNotProvidedExceptionHandler(CarNotProvidedException e) {
        CarNotProvidedResponse registeredResponse = new CarNotProvidedResponse();
        registeredResponse.setMessage(e.getMessage());
        registeredResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        return registeredResponse;
    }
}
