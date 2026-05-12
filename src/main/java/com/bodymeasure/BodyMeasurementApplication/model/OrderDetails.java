package com.bodymeasure.BodyMeasurementApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    private Integer id;
    private Date date;
    private Time time;
    private String description;
    private String gender;
    private Double netTotal;
    private String customerId;
    private List<OrderItemDetails> orderItemDetails;
}
