package com.bodymeasure.BodyMeasurementApplication.repository.impl;

import com.bodymeasure.BodyMeasurementApplication.model.OrderItemDetails;
import com.bodymeasure.BodyMeasurementApplication.repository.OrderItemDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemDetailsRepositoryImpl implements OrderItemDetailsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean saveOrderItemDetails(OrderItemDetails orderItemDetails) {

        String sql = "INSERT INTO order_item_details " +
                "(order_id, item_id, qty, total, fabric, description) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                orderItemDetails.getOrderId(),
                orderItemDetails.getItemId(),
                orderItemDetails.getQty(),
                orderItemDetails.getTotal(),
                orderItemDetails.getFabric(),
                orderItemDetails.getDescription()
        ) > 0;
    }
}
