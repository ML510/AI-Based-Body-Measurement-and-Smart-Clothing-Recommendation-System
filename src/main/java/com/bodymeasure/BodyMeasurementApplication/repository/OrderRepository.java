package com.bodymeasure.BodyMeasurementApplication.repository;

import com.bodymeasure.BodyMeasurementApplication.model.Order;

public interface OrderRepository {
    boolean saveOrder(Order order);

}
