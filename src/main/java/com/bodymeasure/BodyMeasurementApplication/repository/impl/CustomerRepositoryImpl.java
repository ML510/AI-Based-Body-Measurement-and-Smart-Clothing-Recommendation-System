package com.bodymeasure.BodyMeasurementApplication.repository.impl;

import com.bodymeasure.BodyMeasurementApplication.model.Customer;
import com.bodymeasure.BodyMeasurementApplication.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean saveCustomer(Customer customer) {

        String sql = "INSERT INTO customer (name, email, address, phoneNumber) VALUES (?, ?, ?, ?)";

        int rows = jdbcTemplate.update(sql,
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhoneNumber()
        );

        return rows > 0;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET name = ?, email = ?, address = ?, phoneNumber = ? WHERE id = ?";

        return jdbcTemplate.update(sql,
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhoneNumber(),
                customer.getId()
        )> 0;
    }

    @Override
    public List<Customer> getLimitCustomer(Integer lastId, Integer limit) {
        String sql = "SELECT * FROM customer WHERE id > ? ORDER BY id ASC LIMIT ?";

        return jdbcTemplate.query(sql, new Object[]{lastId, limit}, (rs, rowNum) -> new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("phoneNumber")
        ));

    }
}
