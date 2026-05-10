package com.bodymeasure.BodyMeasurementApplication.repository;

import com.bodymeasure.BodyMeasurementApplication.model.Customer;

import java.util.List;

public interface CustomerRepository {
    boolean saveCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    List<Customer> getLimitCustomer(Integer lastId, Integer limit);
}
