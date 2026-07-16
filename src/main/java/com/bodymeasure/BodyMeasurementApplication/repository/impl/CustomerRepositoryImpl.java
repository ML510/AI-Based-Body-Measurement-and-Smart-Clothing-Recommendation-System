package com.bodymeasure.BodyMeasurementApplication.repository.impl;

import com.bodymeasure.BodyMeasurementApplication.model.Customer;
import com.bodymeasure.BodyMeasurementApplication.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

        private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean saveCustomer(Customer customer) {
        String sql = "INSERT INTO customer (name, email, address, phoneNumber) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhoneNumber()
        )>0;
    }
}
