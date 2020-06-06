package com.example.TaxiApp.DTO.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarNotProvidedException extends Exception {
    private String message;
    public CarNotProvidedException(String s) {
        message=s;
    }
}
