package com.bodymeasure.BodyMeasurementApplication.controller;

import com.bodymeasure.BodyMeasurementApplication.model.Customer;
import com.bodymeasure.BodyMeasurementApplication.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/save")
    public boolean saveCustomer(@RequestBody Customer customer){
        log.info("Customer backend: {}", customer);
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/test")
    public boolean test(){
        return true;
    }
}
