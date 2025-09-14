package com.sinaukoding.finalproject.foodordersystem.model.request;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Role;
import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;
import jakarta.validation.constraints.NotBlank;

public record AdminRequestRecord(String id,
                                 @NotBlank(message = "Nama tidak boleh kosong") String name,
                                 @NotBlank(message = "Username tidak boleh kosong") String username,
                                 @NotBlank(message = "Email tidak boleh kosong") String email,
                                 @NotBlank(message = "Password tidak boleh kosong") String password,
                                 Status status,
                                 Role role) {
}
