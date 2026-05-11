package com.bodymeasure.BodyMeasurementApplication.repository.impl;

import com.bodymeasure.BodyMeasurementApplication.model.AiConfidence;
import com.bodymeasure.BodyMeasurementApplication.model.MenMeasurement;
import com.bodymeasure.BodyMeasurementApplication.repository.MenMeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenMeasurementRepositoryImpl implements MenMeasurementRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean saveMenMeasurement(MenMeasurement measurement) {

        String sql = "INSERT INTO men_measurement (" +
                "neck, shoulder, chest, waist, full_length, sleeve_length, armhole, " +
                "hip, inseam, outseam, thigh, knee, cuff, cross_back, wrist, " +
                "head_circumference, head_height, ai_confidence, orderId" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,

                measurement.getNeck(),
                measurement.getShoulder(),
                measurement.getChest(),
                measurement.getWaist(),

                measurement.getFullLength(),
                measurement.getSleeveLength(),
                measurement.getArmhole(),

                measurement.getHip(),
                measurement.getInseam(),
                measurement.getOutseam(),
                measurement.getThigh(),
                measurement.getKnee(),
                measurement.getCuff(),

                measurement.getCrossBack(),
                measurement.getWrist(),

                measurement.getHeadCircumference(),
                measurement.getHeadHeight(),

                measurement.getAiConfidence().name(),

                measurement.getOrderId()
        )> 0;
    }

    @Override
    public boolean updateMenMeasurement(MenMeasurement measurement) {

        String sql = "UPDATE men_measurement SET neck = ?, shoulder = ?, chest = ?, waist = ?, full_length = ?, sleeve_length = ?, " +
                "armhole = ?, hip = ?, inseam = ?, outseam = ?, thigh = ?, knee = ?, cuff = ?, cross_back = ?, wrist = ?, head_circumference = ?, " +
                "head_height = ?, ai_confidence = ?, orderId = ? WHERE id = ?";

        return jdbcTemplate.update(sql,
                measurement.getNeck(),
                measurement.getShoulder(),
                measurement.getChest(),
                measurement.getWaist(),
                measurement.getFullLength(),
                measurement.getSleeveLength(),
                measurement.getArmhole(),
                measurement.getHip(),
                measurement.getInseam(),
                measurement.getOutseam(),
                measurement.getThigh(),
                measurement.getKnee(),
                measurement.getCuff(),
                measurement.getCrossBack(),
                measurement.getWrist(),
                measurement.getHeadCircumference(),
                measurement.getHeadHeight(),
                measurement.getAiConfidence().name(),
                measurement.getOrderId(),
                measurement.getId()
        ) > 0;
    }

    @Override
    public List<MenMeasurement> searchByOrderId(String id) {

        String sql = "SELECT * FROM men_measurement WHERE orderId = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new MenMeasurement(

                rs.getInt("id"),

                rs.getBigDecimal("neck"),
                rs.getBigDecimal("shoulder"),
                rs.getBigDecimal("chest"),
                rs.getBigDecimal("waist"),

                rs.getBigDecimal("full_length"),
                rs.getBigDecimal("sleeve_length"),
                rs.getBigDecimal("armhole"),

                rs.getBigDecimal("hip"),
                rs.getBigDecimal("inseam"),
                rs.getBigDecimal("outseam"),
                rs.getBigDecimal("thigh"),
                rs.getBigDecimal("knee"),
                rs.getBigDecimal("cuff"),

                rs.getBigDecimal("cross_back"),

                rs.getBigDecimal("wrist"),

                rs.getBigDecimal("head_circumference"),

                rs.getBigDecimal("head_height"),

                AiConfidence.valueOf(rs.getString("ai_confidence")),

                rs.getString("orderId")

        ), id);
    }
}
