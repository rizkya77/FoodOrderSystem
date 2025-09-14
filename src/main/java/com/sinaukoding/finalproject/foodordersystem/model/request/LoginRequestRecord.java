package com.sinaukoding.finalproject.foodordersystem.model.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestRecord(@NotBlank String username,
                                 @NotBlank String password) {
}
