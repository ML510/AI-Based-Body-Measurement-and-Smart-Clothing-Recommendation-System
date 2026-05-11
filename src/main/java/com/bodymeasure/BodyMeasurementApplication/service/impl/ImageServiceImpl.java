package com.bodymeasure.BodyMeasurementApplication.service.impl;

import com.bodymeasure.BodyMeasurementApplication.model.Image;
import com.bodymeasure.BodyMeasurementApplication.repository.ImageRepository;
import com.bodymeasure.BodyMeasurementApplication.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public boolean imageSave(Image image) {
        return imageRepository.imageSave(image);
    }

    @Override
    public Image imageSearchByOrderId(String orderId) {
        return imageRepository.imageSearchByOrderId(orderId);
    }

    @Override
    public ResponseEntity<byte[]> getImage(String id) {
        return imageRepository.getImage(id);
    }
}
