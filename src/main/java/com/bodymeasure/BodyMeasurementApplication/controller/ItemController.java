package com.bodymeasure.BodyMeasurementApplication.controller;

import com.bodymeasure.BodyMeasurementApplication.model.Item;
import com.bodymeasure.BodyMeasurementApplication.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/save")
    public boolean saveItem(@RequestBody Item item){
        return itemService.saveItem(item);
    }

    @PutMapping("/update")
    public boolean updateItem(@RequestBody Item item){
        return itemService.updateItem(item);
    }

    @GetMapping("/getItemName")
    List<String> getItemName(){
        return itemService.getItemName();
    }
}
