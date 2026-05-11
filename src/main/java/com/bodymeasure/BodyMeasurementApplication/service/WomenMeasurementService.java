package com.bodymeasure.BodyMeasurementApplication.service;

import com.bodymeasure.BodyMeasurementApplication.model.WomenMeasurement;

import java.util.List;

public interface WomenMeasurementService {
    boolean saveWomenMeasurement(WomenMeasurement measurement);

    boolean updateWomenMeasurement(WomenMeasurement measurement);

    List<WomenMeasurement> searchByOrderIdWomenMeasurement(String orderId);
}
