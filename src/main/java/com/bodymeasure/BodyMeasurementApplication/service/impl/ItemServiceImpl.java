package com.bodymeasure.BodyMeasurementApplication.service.impl;

import com.bodymeasure.BodyMeasurementApplication.model.Item;
import com.bodymeasure.BodyMeasurementApplication.repository.ItemRepository;
import com.bodymeasure.BodyMeasurementApplication.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public boolean saveItem(Item item) {
        return itemRepository.saveItem(item);
    }

    @Override
    public boolean updateItem(Item item) {
        return itemRepository.updateItem(item);
    }

    @Override
    public List<String> getItemName() {
        return itemRepository.getItemName();
    }
}
