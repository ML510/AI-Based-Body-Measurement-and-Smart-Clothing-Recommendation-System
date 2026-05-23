package com.bodymeasure.BodyMeasurementApplication.service;

import com.bodymeasure.BodyMeasurementApplication.dto.MeasurementResultDTO;

import java.util.List;

public interface GeminiAiService {
    MeasurementResultDTO analyzeMeasurements(String base64Image, String imageMediaType, String gender, List<String> clothingCodes, Double heightCm);
}
