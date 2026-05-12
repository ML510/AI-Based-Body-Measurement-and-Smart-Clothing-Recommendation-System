package com.bodymeasure.BodyMeasurementApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDetails {

    private String orderId;
    private String itemId;
    private Integer qty;
    private Double total;
    private String fabric;
    private String description;
}
