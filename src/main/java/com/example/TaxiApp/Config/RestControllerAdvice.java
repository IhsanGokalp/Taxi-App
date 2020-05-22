package com.example.TaxiApp.Config;

import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanSavedDto;
import com.example.TaxiApp.DTO.error.ValidationExceptionDto;
import com.example.TaxiApp.DTO.error.ValidationExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(ValidationExceptionDto.class)
    public ValidationExceptionResponse methodArgumentNotValidExceptionHandler(
            ValidationExceptionDto e) {
        ValidationExceptionResponse exceptionDto = new ValidationExceptionResponse();
        exceptionDto.setMessage("Maximum number of seats for "+ e.getDriverPlanId() +
                " with " + e.getDriverDto().getId() + " is " + e.getCarSits());
        return exceptionDto;
    }
}
