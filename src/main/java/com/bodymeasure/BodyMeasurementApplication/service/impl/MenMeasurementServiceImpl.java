package com.bodymeasure.BodyMeasurementApplication.service.impl;

import com.bodymeasure.BodyMeasurementApplication.model.MenMeasurement;
import com.bodymeasure.BodyMeasurementApplication.repository.MenMeasurementRepository;
import com.bodymeasure.BodyMeasurementApplication.service.MenMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenMeasurementServiceImpl implements MenMeasurementService {

    private final MenMeasurementRepository menMeasurementRepository;

    @Override
    public boolean saveMenMeasurement(MenMeasurement measurement) {
        return menMeasurementRepository.saveMenMeasurement(measurement);
    }

    @Override
    public boolean updateMenMeasurement(MenMeasurement measurement) {
        return menMeasurementRepository.updateMenMeasurement(measurement);
    }

    @Override
    public List<MenMeasurement> searchByOrderId(String orderId) {
        return menMeasurementRepository.searchByOrderId(orderId);
    }
}
