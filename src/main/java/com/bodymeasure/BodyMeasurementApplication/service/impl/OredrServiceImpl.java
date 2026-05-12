package com.bodymeasure.BodyMeasurementApplication.service.impl;

import com.bodymeasure.BodyMeasurementApplication.model.Order;
import com.bodymeasure.BodyMeasurementApplication.repository.OrderRepository;
import com.bodymeasure.BodyMeasurementApplication.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OredrServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public boolean saveOrder(Order order) {
        return orderRepository.saveOrder(order);
    }
}
