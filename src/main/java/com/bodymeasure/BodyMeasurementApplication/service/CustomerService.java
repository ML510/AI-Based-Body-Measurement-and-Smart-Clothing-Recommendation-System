package com.bodymeasure.BodyMeasurementApplication.service;

import com.bodymeasure.BodyMeasurementApplication.model.Customer;

import java.util.List;

public interface CustomerService {
    boolean saveCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    List<Customer> getLimitCustomer(Integer lastId, Integer limit);
}
