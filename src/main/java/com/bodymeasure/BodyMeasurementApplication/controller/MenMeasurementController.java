package com.bodymeasure.BodyMeasurementApplication.controller;

import com.bodymeasure.BodyMeasurementApplication.model.MenMeasurement;
import com.bodymeasure.BodyMeasurementApplication.service.MenMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/men_measurement")
@RequiredArgsConstructor
public class MenMeasurementController {

    private final MenMeasurementService menMeasurementService;

    @PostMapping("/save")
    public boolean saveMenMeasurement(@RequestBody MenMeasurement measurement){
        return menMeasurementService.saveMenMeasurement(measurement);
    }

    @PutMapping("/update")
    public boolean updateMenMeasurement(@RequestBody MenMeasurement measurement){
        return menMeasurementService.updateMenMeasurement(measurement);
    }

    @GetMapping("/searchByOrderId/{orderId}")
    public List<MenMeasurement> searchByOrderId(@PathVariable String orderId){
        return menMeasurementService.searchByOrderId(orderId);
    }


}
