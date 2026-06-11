package com.bodymeasure.BodyMeasurementApplication.controller;

import com.bodymeasure.BodyMeasurementApplication.dto.MeasurementResultDTO;
import com.bodymeasure.BodyMeasurementApplication.service.GeminiAiService;
import com.bodymeasure.BodyMeasurementApplication.service.MenMeasurementService;
import com.bodymeasure.BodyMeasurementApplication.service.WomenMeasurementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/scan")
@RequiredArgsConstructor
public class ScanController {

    private final GeminiAiService geminiAiService;
    private final MenMeasurementService menMeasurementService;
    private final WomenMeasurementService womenMeasurementService;

    @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> analyzeScan(
            @RequestPart("image") MultipartFile image,
            @RequestParam("gender") String gender,
            @RequestParam("clothingCodes") List<String> clothingCodes,
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "heightCm", required = false) Double heightCm
    ) {
        try {

            if (image == null || image.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Image is required"));
            }


            if (gender == null ||
                    (!"MEN".equalsIgnoreCase(gender) && !"WOMEN".equalsIgnoreCase(gender))) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Gender must be MEN or WOMEN"));
            }

            if (clothingCodes == null || clothingCodes.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "clothingCodes is required"));
            }

            byte[] bytes = image.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(bytes);

            String mediaType = (image.getContentType() != null)
                    ? image.getContentType()
                    : "image/jpeg";

            log.info("Analyzing scan | gender={} | clothingCodes={} | customerId={}",
                    gender, clothingCodes, customerId);

            MeasurementResultDTO result =
                    geminiAiService.analyzeMeasurements(
                            base64Image,
                            mediaType,
                            gender.toUpperCase(),
                            clothingCodes,
                            heightCm
                    );

            if (customerId != null) {
                log.info("Customer scan processed | customerId={} | result={}",
                        customerId, result);

                // future use: save to DB etc.
                // measurementService.save(customerId, result);
            }

            return ResponseEntity.ok(result);

        } catch (IOException e) {
            log.error("IO error during scan processing", e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to process image"));

        } catch (Exception e) {
            log.error("Unexpected scan analysis error", e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Scan failed: " + e.getMessage()));
        }
    }



}
