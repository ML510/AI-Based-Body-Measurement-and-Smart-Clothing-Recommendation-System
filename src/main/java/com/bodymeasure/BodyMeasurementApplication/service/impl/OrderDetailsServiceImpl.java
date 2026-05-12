package com.bodymeasure.BodyMeasurementApplication.service.impl;

import com.bodymeasure.BodyMeasurementApplication.model.OrderDetails;
import com.bodymeasure.BodyMeasurementApplication.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Override
    public boolean saveOrderDetails(OrderDetails orderDetails) {
        return false;
    }
}
