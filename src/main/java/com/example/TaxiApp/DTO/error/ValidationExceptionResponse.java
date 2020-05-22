package com.example.TaxiApp.DTO.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationExceptionResponse {
    private Integer code;
    private String message;
}
