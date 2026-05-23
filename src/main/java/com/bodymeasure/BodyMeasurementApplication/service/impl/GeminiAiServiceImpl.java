package com.bodymeasure.BodyMeasurementApplication.service.impl;

import com.bodymeasure.BodyMeasurementApplication.dto.MeasurementResultDTO;
import com.bodymeasure.BodyMeasurementApplication.service.GeminiAiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeminiAiServiceImpl implements GeminiAiService {
    @Value("${app.gemini.api-key}")
    private String geminiApiKey;        // ← application.properties ඉඳලා

    @Value("${app.gemini.model}")
    private String geminiModel;         // ← gemini-1.5-flash-latest

    private final ObjectMapper objectMapper;    // ← JSON parse
    private final WebClient.Builder webClientBuilder; // ← HTTP calls

    // Rate limit වුණොත් try කරන models
    private static final List<String> FALLBACK_MODELS = List.of(
            "gemini-1.5-flash-latest",
            "gemini-1.5-flash-8b",
            "gemini-1.5-pro-latest"
    );


    @Override
    public MeasurementResultDTO analyzeMeasurements(String base64Image, String imageMediaType, String gender, List<String> clothingCodes, Double heightCm) {

        //Create Prompt
        String prompt = buildPrompt(gender, clothingCodes, heightCm);

        //Create Request Body (Gemini)
        Map<String, Object> requestBody = buildGeminiRequest(base64Image, imageMediaType, prompt);

        // Try configured model first, then fallbacks
        List<String> modelsToTry = new ArrayList<>();
        modelsToTry.add(geminiModel);
        FALLBACK_MODELS.forEach(m -> { if (!m.equals(geminiModel)) modelsToTry.add(m); });

        Exception lastException = null;

        for (String model : modelsToTry) {
            try {
                log.info("Trying Gemini model: {}", model);

                //Get HTTP POST in Gemini And Seen AI response
                String response = callGemini(model, requestBody);


                return parseGeminiResponse(response, gender);

            } catch (WebClientResponseException e) {
                if (e.getStatusCode().value() == 429) {
                    log.warn("Rate limited on model {}. Trying next model...", model);
                    lastException = e;
                    // Wait 2 seconds before trying next model
                    try { Thread.sleep(2000); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
                    continue;
                }
                log.error("Gemini API error on model {}: {}", model, e.getMessage());
                throw new RuntimeException("AI body scan failed: " + e.getMessage());
            } catch (Exception e) {
                log.error("Gemini scan failed on model {}", model, e);
                lastException = e;
            }
        }

        throw new RuntimeException("All Gemini models rate limited. Please wait 1 minute and try again. " +
                "(429 Too Many Requests - Free tier limit reached)");

    }

    private String buildPrompt(String gender, List<String> clothingCodes, Double heightCm) {
        StringBuilder sb = new StringBuilder();
        sb.append("You are an expert tailor and body measurement specialist.\n");
        sb.append("Carefully analyze this photo of a person and estimate ALL body measurements in centimeters.\n\n");

        if (heightCm != null) {
            sb.append("IMPORTANT: The person's actual height is ").append(heightCm)
                    .append(" cm. Use this as reference to calibrate all other measurements accurately.\n\n");
        }

        sb.append("Gender: ").append(gender).append("\n");
        sb.append("Selected clothing types: ").append(String.join(", ", clothingCodes)).append("\n\n");

        if ("MEN".equals(gender)) {
            sb.append(MEN_INSTRUCTIONS);
            sb.append("\n\nReturn ONLY this JSON structure with estimated values:\n");
            sb.append(MEN_JSON_TEMPLATE);
        } else {
            sb.append(WOMEN_INSTRUCTIONS);
            sb.append("\n\nReturn ONLY this JSON structure with estimated values:\n");
            sb.append(WOMEN_JSON_TEMPLATE);
        }

        return sb.toString();
    }


    private Map<String, Object> buildGeminiRequest(String base64Image, String mediaType, String prompt) {
        Map<String, Object> imageData = new LinkedHashMap<>();
        imageData.put("mime_type", mediaType);
        imageData.put("data", base64Image);

        Map<String, Object> inlineData = new LinkedHashMap<>();
        inlineData.put("inline_data", imageData);

        Map<String, Object> textPart = new LinkedHashMap<>();
        textPart.put("text", prompt);

        Map<String, Object> content = new LinkedHashMap<>();
        content.put("parts", List.of(inlineData, textPart));

        Map<String, Object> generationConfig = new LinkedHashMap<>();
        generationConfig.put("temperature", 0.1);
        generationConfig.put("maxOutputTokens", 2000);
        generationConfig.put("responseMimeType", "application/json");

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("contents", List.of(content));
        body.put("generationConfig", generationConfig);

        return body;
    }

    private String callGemini(String model, Map<String, Object> requestBody) {
        WebClient client = webClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        String url = "/v1beta/models/" + model + ":generateContent?key=" + geminiApiKey;

        return client.post()
                .uri(url)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


    private MeasurementResultDTO parseGeminiResponse(String rawResponse, String gender) throws Exception {
        JsonNode root = objectMapper.readTree(rawResponse);

        String text = root
                .path("candidates").get(0)
                .path("content")
                .path("parts").get(0)
                .path("text").asText();

        text = text.replaceAll("```json", "").replaceAll("```", "").trim();
        log.debug("Gemini response JSON: {}", text);

        JsonNode data = objectMapper.readTree(text);
        MeasurementResultDTO result = new MeasurementResultDTO();
        result.setGender(gender);

        result.setNeck(getDouble(data, "neck"));
        result.setShoulder(getDouble(data, "shoulder"));
        result.setChest(getDouble(data, "chest"));
        result.setWaist(getDouble(data, "waist"));
        result.setFullLength(getDouble(data, "full_length"));
        result.setSleeveLength(getDouble(data, "sleeve_length"));
        result.setArmhole(getDouble(data, "armhole"));
        result.setHip(getDouble(data, "hip"));
        result.setInseam(getDouble(data, "inseam"));
        result.setOutseam(getDouble(data, "outseam"));
        result.setThigh(getDouble(data, "thigh"));
        result.setKnee(getDouble(data, "knee"));
        result.setCuff(getDouble(data, "cuff"));
        result.setCrossBack(getDouble(data, "cross_back"));
        result.setWrist(getDouble(data, "wrist"));

        if ("MEN".equals(gender)) {
            result.setHeadCircumference(getDouble(data, "head_circumference"));
            result.setHeadHeight(getDouble(data, "head_height"));
        } else {
            result.setBust(getDouble(data, "bust"));
            result.setSleeveOpening(getDouble(data, "sleeve_opening"));
            result.setAnkle(getDouble(data, "ankle"));
            result.setShoulderToWaist(getDouble(data, "shoulder_to_waist"));
            result.setSkirtLength(getDouble(data, "skirt_length"));
            result.setApexPoint(getDouble(data, "apex_point"));
            result.setNeckDepthFront(getDouble(data, "neck_depth_front"));
            result.setNeckDepthBack(getDouble(data, "neck_depth_back"));
            result.setSideSplitHeight(getDouble(data, "side_slit_height"));
            result.setUpperBust(getDouble(data, "upper_bust"));
            result.setUnderBust(getDouble(data, "under_bust"));
            result.setArmLength(getDouble(data, "arm_length"));
        }

        result.setAiConfidence(data.path("confidence").asText("MEDIUM").toUpperCase());
        result.setNotes(data.path("notes").asText(""));

        return result;
    }

    private Double getDouble(JsonNode node, String field) {
        JsonNode v = node.path(field);
        return v.isMissingNode() || v.isNull() ? null : v.asDouble();
    }










    private static final String MEN_INSTRUCTIONS =
            "Measure the following (all in cm):\n" +
                    "UPPER BODY: neck, shoulder (bone to bone), chest (widest), waist, full_length (neck to hem), sleeve_length, armhole\n" +
                    "LOWER BODY: hip, inseam (crotch to ankle), outseam (waist to ankle), thigh (widest), knee, cuff (leg opening)\n" +
                    "FORMAL: cross_back (shoulder blade to shoulder blade), wrist\n" +
                    "HOOD: head_circumference, head_height\n";

    private static final String WOMEN_INSTRUCTIONS =
            "Measure the following (all in cm):\n" +
                    "UPPER BODY: shoulder, bust (fullest), waist (narrowest), armhole, sleeve_length, sleeve_opening, full_length\n" +
                    "LOWER BODY: hip (widest), thigh, inseam, outseam, knee, ankle\n" +
                    "DRESS: shoulder_to_waist, skirt_length, apex_point (shoulder to nipple)\n" +
                    "TRADITIONAL: neck_depth_front, neck_depth_back, side_slit_height\n" +
                    "SPECIAL: cross_back, upper_bust, under_bust, arm_length, wrist\n";


    private static final String MEN_JSON_TEMPLATE =
            "{\"neck\":0,\"shoulder\":0,\"chest\":0,\"waist\":0,\"full_length\":0," +
                    "\"sleeve_length\":0,\"armhole\":0,\"hip\":0,\"inseam\":0,\"outseam\":0," +
                    "\"thigh\":0,\"knee\":0,\"cuff\":0,\"cross_back\":0,\"wrist\":0," +
                    "\"head_circumference\":0,\"head_height\":0," +
                    "\"confidence\":\"HIGH\",\"notes\":\"brief note\"}";

    private static final String WOMEN_JSON_TEMPLATE =
            "{\"shoulder\":0,\"bust\":0,\"waist\":0,\"armhole\":0,\"sleeve_length\":0," +
                    "\"sleeve_opening\":0,\"full_length\":0,\"hip\":0,\"thigh\":0,\"inseam\":0," +
                    "\"outseam\":0,\"knee\":0,\"ankle\":0,\"shoulder_to_waist\":0,\"skirt_length\":0," +
                    "\"apex_point\":0,\"neck_depth_front\":0,\"neck_depth_back\":0,\"side_slit_height\":0," +
                    "\"cross_back\":0,\"upper_bust\":0,\"under_bust\":0,\"arm_length\":0,\"wrist\":0," +
                    "\"confidence\":\"HIGH\",\"notes\":\"brief note\"}";
}
