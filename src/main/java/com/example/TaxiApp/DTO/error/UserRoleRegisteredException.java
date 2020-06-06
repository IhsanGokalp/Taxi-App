package com.example.TaxiApp.DTO.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleRegisteredException extends Exception {
    private String message;
    public UserRoleRegisteredException(String s) {
        message = s;
    }
}
