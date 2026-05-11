package com.bodymeasure.BodyMeasurementApplication.service;

import com.bodymeasure.BodyMeasurementApplication.model.Image;
import org.springframework.http.ResponseEntity;

public interface ImageService {
    boolean imageSave(Image image);

    Image imageSearchByOrderId(String orderId);

    ResponseEntity<byte[]> getImage(String id);
}
