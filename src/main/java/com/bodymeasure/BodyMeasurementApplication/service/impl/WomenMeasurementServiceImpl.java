package com.bodymeasure.BodyMeasurementApplication.service.impl;

import com.bodymeasure.BodyMeasurementApplication.model.WomenMeasurement;
import com.bodymeasure.BodyMeasurementApplication.repository.WomenMeasurementRepository;
import com.bodymeasure.BodyMeasurementApplication.service.WomenMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WomenMeasurementServiceImpl implements WomenMeasurementService {

    private final WomenMeasurementRepository womenMeasurementRepository;

    @Override
    public boolean saveWomenMeasurement(WomenMeasurement measurement) {
        return womenMeasurementRepository.saveWomenMeasurement(measurement);
    }

    @Override
    public boolean updateWomenMeasurement(WomenMeasurement measurement) {
        return womenMeasurementRepository.updateWomenMeasurement(measurement);
    }

    @Override
    public List<WomenMeasurement> searchByOrderIdWomenMeasurement(String orderId) {
        return womenMeasurementRepository.searchByOrderIdWomenMeasurement(orderId);
    }
}
