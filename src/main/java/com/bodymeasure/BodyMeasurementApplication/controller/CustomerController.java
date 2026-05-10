package com.bodymeasure.BodyMeasurementApplication.controller;

import com.bodymeasure.BodyMeasurementApplication.model.Customer;
import com.bodymeasure.BodyMeasurementApplication.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/save")
    public boolean saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @PostMapping("/update")
    public boolean updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @GetMapping("/get-all/{lastId}/{limit}")
    public List<Customer> getLimitCustomer(@PathVariable Integer lastId, @PathVariable Integer limit){
        return customerService.getLimitCustomer(lastId,limit);
    }


    @GetMapping("/test")
    public boolean test(){
        return true;
    }
}
