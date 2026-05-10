package com.bodymeasure.BodyMeasurementApplication.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenMeasurement {

    private Integer id;

    // ---- Upper Body (cm) ----
    private BigDecimal neck;
    private BigDecimal shoulder;
    private BigDecimal chest;
    private BigDecimal waist;

    private BigDecimal fullLength;
    private BigDecimal sleeveLength;
    private BigDecimal armhole;

    // ---- Lower Body (cm) ----
    private BigDecimal hip;
    private BigDecimal inseam;
    private BigDecimal outseam;
    private BigDecimal thigh;
    private BigDecimal knee;
    private BigDecimal cuff;

    // ---- Formal/Suit (cm) ----
    private BigDecimal crossBack;

    private BigDecimal wrist;

    // ---- Hood measurements (Hoodies) ----
    private BigDecimal headCircumference;

    private BigDecimal headHeight;

    @Enumerated(EnumType.STRING)
    private AiConfidence aiConfidence = AiConfidence.MEDIUM;
}
