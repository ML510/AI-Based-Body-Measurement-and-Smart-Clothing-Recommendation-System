package com.bodymeasure.BodyMeasurementApplication.repository.impl;

import com.bodymeasure.BodyMeasurementApplication.model.Customer;
import com.bodymeasure.BodyMeasurementApplication.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public boolean saveCustomer(Customer customer) {
        return false;
    }
}
