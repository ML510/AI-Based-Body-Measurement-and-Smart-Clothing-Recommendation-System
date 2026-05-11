package com.bodymeasure.BodyMeasurementApplication.repository;

import com.bodymeasure.BodyMeasurementApplication.model.Item;

import java.util.List;

public interface ItemRepository {
    boolean saveItem(Item item);

    boolean updateItem(Item item);

    List<String> getItemName();

}
