package com.bodymeasure.BodyMeasurementApplication.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WomenMeasurement {

    private Integer id;

    // ---- Upper Body ----
    private BigDecimal shoulder;
    private BigDecimal bust;
    private BigDecimal waist;
    private BigDecimal armhole;

    private BigDecimal sleeveLength;

    private BigDecimal sleeveOpening;

    private BigDecimal fullLength;

    // ---- Lower Body ----
    private BigDecimal hip;
    private BigDecimal thigh;
    private BigDecimal inseam;
    private BigDecimal outseam;
    private BigDecimal knee;
    private BigDecimal ankle;

    // ---- Dress/Full Outfit ----
    private BigDecimal shoulderToWaist;


    private BigDecimal skirtLength;


    private BigDecimal apexPoint;

    // ---- Traditional ----
    private BigDecimal neckDepthFront;

    private BigDecimal neckDepthBack;


    private BigDecimal sideSplitHeight;

    // ---- Special ----
    private BigDecimal crossBack;

    private BigDecimal upperBust;

    private BigDecimal underBust;

    private BigDecimal armLength;

    private BigDecimal wrist;

    private AiConfidence aiConfidence = AiConfidence.MEDIUM;

    private String orderId;
}
