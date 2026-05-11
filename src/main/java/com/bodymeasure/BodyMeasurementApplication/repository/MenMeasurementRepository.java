package com.bodymeasure.BodyMeasurementApplication.repository;

import com.bodymeasure.BodyMeasurementApplication.model.MenMeasurement;

import java.util.List;

public interface MenMeasurementRepository {
    boolean saveMenMeasurement(MenMeasurement measurement);

    boolean updateMenMeasurement(MenMeasurement measurement);

    List<MenMeasurement> searchByOrderId(String orderId);
}
