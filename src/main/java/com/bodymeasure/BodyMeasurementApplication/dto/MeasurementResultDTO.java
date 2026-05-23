package com.bodymeasure.BodyMeasurementApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementResultDTO {

    private String gender;
    private String aiConfidence;
    private String notes;

    // Common
    private Double shoulder;
    private Double waist;
    private Double hip;
    private Double thigh;
    private Double knee;
    private Double inseam;
    private Double outseam;
    private Double armhole;
    private Double sleeveLength;
    private Double fullLength;
    private Double crossBack;
    private Double wrist;

    // Men specific
    private Double neck;
    private Double chest;
    private Double cuff;
    private Double headCircumference;
    private Double headHeight;

    // Women specific
    private Double bust;
    private Double sleeveOpening;
    private Double ankle;
    private Double shoulderToWaist;
    private Double skirtLength;
    private Double apexPoint;
    private Double neckDepthFront;
    private Double neckDepthBack;
    private Double sideSplitHeight;
    private Double upperBust;
    private Double underBust;
    private Double armLength;
}
