package com.bodymeasure.BodyMeasurementApplication.repository;

import com.bodymeasure.BodyMeasurementApplication.model.Customer;

public interface CustomerRepository {
    boolean saveCustomer(Customer customer);
}
