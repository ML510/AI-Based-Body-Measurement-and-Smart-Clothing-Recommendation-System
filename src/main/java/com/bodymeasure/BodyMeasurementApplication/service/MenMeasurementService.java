package com.bodymeasure.BodyMeasurementApplication.service;

import com.bodymeasure.BodyMeasurementApplication.model.MenMeasurement;

import java.util.List;

public interface MenMeasurementService {
    boolean saveMenMeasurement(MenMeasurement measurement);

    boolean updateMenMeasurement(MenMeasurement measurement);

    List<MenMeasurement> searchByOrderId(String orderId);
}
