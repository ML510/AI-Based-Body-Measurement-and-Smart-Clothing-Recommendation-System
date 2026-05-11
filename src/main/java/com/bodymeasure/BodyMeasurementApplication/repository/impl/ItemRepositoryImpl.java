package com.bodymeasure.BodyMeasurementApplication.repository.impl;

import com.bodymeasure.BodyMeasurementApplication.model.Item;
import com.bodymeasure.BodyMeasurementApplication.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean saveItem(Item item) {

        String sql = "INSERT INTO items (item_name, price) VALUES (?, ?)";

        return jdbcTemplate.update(sql,
                item.getItemName(),
                item.getPrice()
        ) > 0;
    }

    @Override
    public boolean updateItem(Item item) {

        String sql = "UPDATE items SET item_name = ?, price = ? WHERE id = ?";

        return jdbcTemplate.update(sql,
                item.getItemName(),
                item.getPrice(),
                item.getId()
        )>0;
    }

    @Override
    public List<String> getItemName() {

        String sql = "SELECT item_name FROM items";

        return jdbcTemplate.queryForList(sql, String.class);
    }
}
