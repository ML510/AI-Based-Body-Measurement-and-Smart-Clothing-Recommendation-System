package com.bodymeasure.BodyMeasurementApplication.service;

import com.bodymeasure.BodyMeasurementApplication.model.OrderItemDetails;

import java.util.List;

public interface OrderItemDetailsService {

    boolean saveOrderItemDetails(List<OrderItemDetails> orderItemDetails);
    boolean saveOrderItemDetails(OrderItemDetails orderItemDetails);
}
