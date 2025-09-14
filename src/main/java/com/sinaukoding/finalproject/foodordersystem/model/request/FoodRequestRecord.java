package com.sinaukoding.finalproject.foodordersystem.model.request;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FoodRequestRecord(String id,
                                @NotBlank(message = "Nama tidak boleh kosong") String name,
                                String description,
                                @NotNull(message = "Harga tidak boleh kosong") Double price,
                                Integer stock,
                                Status status) {
}
