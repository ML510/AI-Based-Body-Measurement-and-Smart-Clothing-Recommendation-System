package com.bodymeasure.BodyMeasurementApplication.model;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    private Integer id;

    @Lob
    private byte[] image;

    private String orderId;
    private String description;
}
