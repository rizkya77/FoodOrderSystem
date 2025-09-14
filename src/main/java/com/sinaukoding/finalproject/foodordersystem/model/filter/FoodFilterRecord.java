package com.sinaukoding.finalproject.foodordersystem.model.filter;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;

public record FoodFilterRecord(String name,
                               String description,
                               Double price,
                               Integer stock,
                               Status status) {
}
