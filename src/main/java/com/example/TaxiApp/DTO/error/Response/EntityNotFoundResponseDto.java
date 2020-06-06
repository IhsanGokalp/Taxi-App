package com.example.TaxiApp.DTO.error.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundResponseDto {
    private String code;
    private String message;
}
