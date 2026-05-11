package com.bodymeasure.BodyMeasurementApplication.repository.impl;

import com.bodymeasure.BodyMeasurementApplication.model.AiConfidence;
import com.bodymeasure.BodyMeasurementApplication.model.WomenMeasurement;
import com.bodymeasure.BodyMeasurementApplication.repository.WomenMeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WomenMeasurementRepositoryImpl implements WomenMeasurementRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean saveWomenMeasurement(WomenMeasurement measurement) {

        String sql = "INSERT INTO women_measurement (" +
                "shoulder, bust, waist, armhole, sleeve_length, sleeve_opening, full_length, " +
                "hip, thigh, inseam, outseam, knee, ankle, " +
                "shoulder_to_waist, skirt_length, apex_point, " +
                "neck_depth_front, neck_depth_back, side_split_height, " +
                "cross_back, upper_bust, under_bust, arm_length, wrist, " +
                "ai_confidence, order_id" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,

                // ---- Upper Body ----
                measurement.getShoulder(),
                measurement.getBust(),
                measurement.getWaist(),
                measurement.getArmhole(),
                measurement.getSleeveLength(),
                measurement.getSleeveOpening(),
                measurement.getFullLength(),

                // ---- Lower Body ----
                measurement.getHip(),
                measurement.getThigh(),
                measurement.getInseam(),
                measurement.getOutseam(),
                measurement.getKnee(),
                measurement.getAnkle(),

                // ---- Dress/Full Outfit ----
                measurement.getShoulderToWaist(),
                measurement.getSkirtLength(),
                measurement.getApexPoint(),

                // ---- Traditional ----
                measurement.getNeckDepthFront(),
                measurement.getNeckDepthBack(),
                measurement.getSideSplitHeight(),

                // ---- Special ----
                measurement.getCrossBack(),
                measurement.getUpperBust(),
                measurement.getUnderBust(),
                measurement.getArmLength(),
                measurement.getWrist(),

                // ---- AI ----
                measurement.getAiConfidence().name(),

                // ---- Order ----
                measurement.getOrderId()

        ) > 0;
    }

    @Override
    public boolean updateWomenMeasurement(WomenMeasurement measurement) {

        String sql = """
                UPDATE women_measurement SET shoulder = ?, bust = ?, waist = ?, armhole = ?, sleeve_length = ?, sleeve_opening = ?, full_length = ?,
                hip = ?, thigh = ?, inseam = ?, outseam = ?, knee = ?, ankle = ?,
                shoulder_to_waist = ?, skirt_length = ?, apex_point = ?,
                neck_depth_front = ?, neck_depth_back = ?, side_split_height = ?,
                cross_back = ?, upper_bust = ?, under_bust = ?, arm_length = ?, wrist = ?,
                ai_confidence = ?, order_id = ?
                WHERE id = ?
                """;

        return jdbcTemplate.update(sql,
                measurement.getShoulder(),
                measurement.getBust(),
                measurement.getWaist(),
                measurement.getArmhole(),
                measurement.getSleeveLength(),
                measurement.getSleeveOpening(),
                measurement.getFullLength(),
                measurement.getHip(),
                measurement.getThigh(),
                measurement.getInseam(),
                measurement.getOutseam(),
                measurement.getKnee(),
                measurement.getAnkle(),
                measurement.getShoulderToWaist(),
                measurement.getSkirtLength(),
                measurement.getApexPoint(),
                measurement.getNeckDepthFront(),
                measurement.getNeckDepthBack(),
                measurement.getSideSplitHeight(),
                measurement.getCrossBack(),
                measurement.getUpperBust(),
                measurement.getUnderBust(),
                measurement.getArmLength(),
                measurement.getWrist(),
                measurement.getAiConfidence().name(),
                measurement.getOrderId(),
                measurement.getId()

        ) > 0;
    }

    @Override
    public List<WomenMeasurement> searchByOrderIdWomenMeasurement(String orderId) {

        String sql = "SELECT * FROM women_measurement WHERE order_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new WomenMeasurement(

                rs.getInt("id"),
                rs.getBigDecimal("shoulder"),
                rs.getBigDecimal("bust"),
                rs.getBigDecimal("waist"),
                rs.getBigDecimal("armhole"),
                rs.getBigDecimal("sleeve_length"),
                rs.getBigDecimal("sleeve_opening"),
                rs.getBigDecimal("full_length"),
                rs.getBigDecimal("hip"),
                rs.getBigDecimal("thigh"),
                rs.getBigDecimal("inseam"),
                rs.getBigDecimal("outseam"),
                rs.getBigDecimal("knee"),
                rs.getBigDecimal("ankle"),
                rs.getBigDecimal("shoulder_to_waist"),
                rs.getBigDecimal("skirt_length"),
                rs.getBigDecimal("apex_point"),
                rs.getBigDecimal("neck_depth_front"),
                rs.getBigDecimal("neck_depth_back"),
                rs.getBigDecimal("side_split_height"),
                rs.getBigDecimal("cross_back"),
                rs.getBigDecimal("upper_bust"),
                rs.getBigDecimal("under_bust"),
                rs.getBigDecimal("arm_length"),
                rs.getBigDecimal("wrist"),
                AiConfidence.valueOf(rs.getString("ai_confidence")),
                rs.getString("order_id")
        ), orderId);
    }
}
