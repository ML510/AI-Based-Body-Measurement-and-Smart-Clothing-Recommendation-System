package com.bodymeasure.BodyMeasurementApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String gender;
    private Double netTotal;
    private String customerId;
}
