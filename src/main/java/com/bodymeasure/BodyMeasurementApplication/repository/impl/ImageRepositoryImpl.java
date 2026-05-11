package com.bodymeasure.BodyMeasurementApplication.repository.impl;

import com.bodymeasure.BodyMeasurementApplication.model.Image;
import com.bodymeasure.BodyMeasurementApplication.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean imageSave(Image image) {

        String sql = "INSERT INTO image (image, orderId, description) VALUES (?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                image.getImage(),
                image.getOrderId(),
                image.getDescription()
        )> 0;
    }

    @Override
    public Image imageSearchByOrderId(String orderId) {

        String sql = "SELECT id, orderId, description FROM image WHERE orderId = ?";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{orderId},
                (rs, rowNum) -> {

                    Image image = new Image();

                    image.setId(rs.getInt("id"));
//              image.setImage(rs.getBytes("image"));
                    image.setOrderId(rs.getString("orderId"));
                    image.setDescription(rs.getString("description"));

                    return image;
                }
        );
    }

    @Override
    public ResponseEntity<byte[]>  getImage(String orderId) {

        String sql = "SELECT image FROM image WHERE orderId = ?";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{orderId},
                (rs, rowNum) -> {

                    byte[] imageBytes = rs.getBytes("image");

                    return ResponseEntity
                            .ok()
                            .contentType(MediaType.IMAGE_JPEG)
                            .body(imageBytes);
                }
        );
    }
}
