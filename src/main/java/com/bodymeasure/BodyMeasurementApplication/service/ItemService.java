package com.bodymeasure.BodyMeasurementApplication.service;

import com.bodymeasure.BodyMeasurementApplication.model.Item;

import java.util.List;

public interface ItemService {
    boolean saveItem(Item item);

    boolean updateItem(Item item);

    List<String> getItemName();

}
