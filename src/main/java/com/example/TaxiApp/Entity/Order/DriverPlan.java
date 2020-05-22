package com.example.TaxiApp.Entity.Order;

import com.example.TaxiApp.Entity.BaseEntity;
import com.example.TaxiApp.Entity.Driver;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "driver_plans")
@Getter
@Setter
public class DriverPlan extends BaseEntity {

    @Column(name = "time")
    private Date time;

    @Column(name = "from_lat")
    private Double fromLat;

    @Column(name = "from_lon")
    private Double fromLon;

    @Column(name = "to_lat")
    private Double toLat;

    @Column(name = "to_lon")
    private Double toLon;

    @Column(name = "available_seats")
    private Integer availableSeats;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(name = "filled")
    private boolean filled;

    @Column(name = "number_of_passengers")
    private Integer numberOfPassengers;


}
