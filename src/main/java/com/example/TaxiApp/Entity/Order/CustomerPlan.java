package com.example.TaxiApp.Entity.Order;

import com.example.TaxiApp.Entity.BaseEntity;
import com.example.TaxiApp.Entity.Customer;
import com.example.TaxiApp.Enums.WhoOrders;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_plans")
@Getter
@Setter
public class CustomerPlan extends BaseEntity {

    @Column(name = "time")
    private Date time;

    @Column(name = "from_lon")
    private Double fromLon;

    @Column(name = "from_lat")
    private Double fromLat;

    @Column(name = "to_lat")
    private Double toLat;

    @Column(name = "to_lon")
    private Double toLon;

    @Enumerated(EnumType.STRING)
    @Column(name = "passenger_type")
    private WhoOrders passengerType;

    @Column(name = "number_of_passengers")
    private Integer numOfPassengers;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "driver_plan_id")
    private DriverPlan driverPlan;
}
