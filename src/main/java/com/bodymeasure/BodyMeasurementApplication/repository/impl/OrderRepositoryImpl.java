package com.bodymeasure.BodyMeasurementApplication.repository.impl;

import com.bodymeasure.BodyMeasurementApplication.model.Order;
import com.bodymeasure.BodyMeasurementApplication.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean saveOrder(Order order) {

        String sql = "INSERT INTO orders " +
                "(order_date, order_time, description, gender, net_total, customer_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                order.getDate(),
                order.getTime(),
                order.getDescription(),
                order.getGender(),
                order.getNetTotal(),
                order.getCustomerId()
        )> 0;
    }
}
