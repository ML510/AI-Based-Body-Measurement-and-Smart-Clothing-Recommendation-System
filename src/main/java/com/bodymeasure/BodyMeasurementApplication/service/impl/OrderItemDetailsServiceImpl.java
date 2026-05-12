package com.bodymeasure.BodyMeasurementApplication.service.impl;

import com.bodymeasure.BodyMeasurementApplication.model.OrderItemDetails;
import com.bodymeasure.BodyMeasurementApplication.repository.OrderItemDetailsRepository;
import com.bodymeasure.BodyMeasurementApplication.service.OrderItemDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemDetailsServiceImpl implements OrderItemDetailsService {

    private final OrderItemDetailsRepository orderItemDetailsRepository;

    @Override
    public boolean saveOrderItemDetails(List<OrderItemDetails> orderItemDetails) {

        for(OrderItemDetails orderIteDetail : orderItemDetails){
            boolean isSave = saveOrderItemDetails(orderIteDetail);
            if (!isSave){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveOrderItemDetails(OrderItemDetails orderItemDetails) {
        return orderItemDetailsRepository.saveOrderItemDetails(orderItemDetails);
    }


}
