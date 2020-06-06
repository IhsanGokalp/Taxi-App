package com.example.TaxiApp.DTO.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerMatchException extends Exception {
    private Long givenCustomerId;
    private Long customerPlanId;

    public CustomerMatchException(Long givenCustomerId, Long customerPlanId) {
        this.givenCustomerId = givenCustomerId;
        this.customerPlanId = customerPlanId;
    }
}
