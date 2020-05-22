package com.example.TaxiApp.Entity;

import com.example.TaxiApp.Enums.Color;
import com.example.TaxiApp.Enums.ColorType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "cars")
@Entity
public class Car extends BaseEntity {

    @Column(name = "licence_plate", unique = true)
    private String licencePlate;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "color_variation")
    private ColorType variationOfColor;

    @Column(name = "model")
    private String model;

    @Column(name = "company")
    private String company;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
