package com.bodymeasure.BodyMeasurementApplication.repository;

import com.bodymeasure.BodyMeasurementApplication.model.OrderItemDetails;

import java.util.List;

public interface OrderItemDetailsRepository {

    boolean saveOrderItemDetails(OrderItemDetails orderItemDetails);
}
