package com.bodymeasure.BodyMeasurementApplication.repository;

import com.bodymeasure.BodyMeasurementApplication.model.Image;
import org.springframework.http.ResponseEntity;

public interface ImageRepository {
    boolean imageSave(Image image);

    Image imageSearchByOrderId(String orderId);

    ResponseEntity<byte[]> getImage(String id);
}
