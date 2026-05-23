package com.bodymeasure.BodyMeasurementApplication.controller;

import com.bodymeasure.BodyMeasurementApplication.dto.MeasurementResultDTO;
import com.bodymeasure.BodyMeasurementApplication.service.GeminiAiService;
import com.bodymeasure.BodyMeasurementApplication.service.MenMeasurementService;
import com.bodymeasure.BodyMeasurementApplication.service.WomenMeasurementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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


    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeScan(@RequestParam("image") MultipartFile image, @RequestParam("gender") String gender, @RequestParam("clothingCodes") List<String> clothingCodes, @RequestParam(value = "customerId", required = false) Long customerId, @RequestParam(value = "heightCm", required = false) Double heightCm){
        try {

            if (image.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Image is required"));
            }

            if (!gender.equals("MEN") && !gender.equals("WOMEN")) {
                return ResponseEntity.badRequest().body(Map.of("error", "Gender must be MEN or WOMEN"));
            }

            // Convert image to base64
            byte[] bytes = image.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(bytes);
            String mediaType = image.getContentType() != null ? image.getContentType() : "image/jpeg";

            log.info("Analyzing body scan | gender={} | clothingCodes={} | customer={}",
                    gender, clothingCodes, customerId);

            //Call Gemini AI
            MeasurementResultDTO result = geminiAiService.analyzeMeasurements(base64Image, mediaType, gender, clothingCodes, heightCm);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            log.error("Scan analysis error", e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Scan failed: " + e.getMessage()));
        }

    }



}
