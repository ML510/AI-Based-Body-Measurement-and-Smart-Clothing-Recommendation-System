package com.bodymeasure.BodyMeasurementApplication.service.impl;

import com.bodymeasure.BodyMeasurementApplication.model.Order;
import com.bodymeasure.BodyMeasurementApplication.model.OrderDetails;
import com.bodymeasure.BodyMeasurementApplication.service.OrderDetailsService;
import com.bodymeasure.BodyMeasurementApplication.service.OrderItemDetailsService;
import com.bodymeasure.BodyMeasurementApplication.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderService orderService;
    private final OrderItemDetailsService orderItemDetailsService;

    @Override
    public boolean saveOrderDetails(OrderDetails od) {


        Order order = new Order(od.getId(), od.getDate(), od.getTime(), od.getDescription(), od.getGender(), od.getNetTotal(), od.getCustomerId());
        boolean isSave = orderService.saveOrder(order);
        if (isSave){
            return orderItemDetailsService.saveOrderItemDetails(od.getOrderItemDetails());
        }
        return false;
    }
}
