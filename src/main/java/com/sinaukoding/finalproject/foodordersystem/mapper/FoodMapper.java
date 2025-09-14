package com.sinaukoding.finalproject.foodordersystem.mapper;

import com.sinaukoding.finalproject.foodordersystem.entity.Food;
import com.sinaukoding.finalproject.foodordersystem.model.request.FoodRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class FoodMapper {

    public Food requestToEntity(FoodRequestRecord request){
        return Food.builder()
                .name(request.name().toUpperCase())
                .description(request.description().toLowerCase())
                .price(request.price())
                .stock(request.stock())
                .status(request.status())
                .build();
    }
}
