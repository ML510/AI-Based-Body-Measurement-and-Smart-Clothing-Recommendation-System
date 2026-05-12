package com.bodymeasure.BodyMeasurementApplication.controller;

import com.bodymeasure.BodyMeasurementApplication.model.OrderDetails;
import com.bodymeasure.BodyMeasurementApplication.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order_details")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @PostMapping("/save")
    public boolean saveOrderDetails(@RequestBody OrderDetails orderDetails){
        return orderDetailsService.saveOrderDetails(orderDetails);

    }
}
