package com.bodymeasure.BodyMeasurementApplication.controller;

import com.bodymeasure.BodyMeasurementApplication.model.Image;
import com.bodymeasure.BodyMeasurementApplication.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean imageSave(
            @RequestParam("file") MultipartFile file,
            @RequestParam("orderId") String orderId,
            @RequestParam("description") String description) {

        try {
            Image image = new Image();

            image.setImage(file.getBytes());
            image.setOrderId(orderId);
            image.setDescription(description);

            return imageService.imageSave(image);

        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/searchByOrderId/{orderId}")
    public Image imageSearchByOrderId(@PathVariable String orderId) {
        return imageService.imageSearchByOrderId(orderId);

    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        return imageService.getImage(id);
    }
}
