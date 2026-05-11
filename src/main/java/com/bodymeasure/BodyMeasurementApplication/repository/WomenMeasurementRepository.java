package com.bodymeasure.BodyMeasurementApplication.repository;

import com.bodymeasure.BodyMeasurementApplication.model.WomenMeasurement;

import java.util.List;

public interface WomenMeasurementRepository {
    boolean saveWomenMeasurement(WomenMeasurement measurement);

    boolean updateWomenMeasurement(WomenMeasurement measurement);

    List<WomenMeasurement> searchByOrderIdWomenMeasurement(String orderId);
}
