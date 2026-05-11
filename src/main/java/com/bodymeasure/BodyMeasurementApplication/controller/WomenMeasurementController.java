package com.bodymeasure.BodyMeasurementApplication.controller;

import com.bodymeasure.BodyMeasurementApplication.model.WomenMeasurement;
import com.bodymeasure.BodyMeasurementApplication.service.WomenMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/women_measurement")
@RequiredArgsConstructor
public class WomenMeasurementController {

    private final WomenMeasurementService womenMeasurementService;

    @PostMapping("/save")
    public boolean saveWomenMeasurement(@RequestBody WomenMeasurement measurement){
        return womenMeasurementService.saveWomenMeasurement(measurement);
    }

    @PutMapping("/update")
    public boolean updateWomenMeasurement(@RequestBody WomenMeasurement measurement){
        return womenMeasurementService.updateWomenMeasurement(measurement);
    }

    @GetMapping("/searchByOrderId/{orderId}")
    public List<WomenMeasurement> searchByOrderIdWomenMeasurement(@PathVariable String orderId){
        return womenMeasurementService.searchByOrderIdWomenMeasurement(orderId);
    }
}
