package com.sinaukoding.finalproject.foodordersystem.model.request;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Role;
import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequestRecord(String id,
                                    @NotBlank(message = "Nama tidak boleh kosong") String name,
                                    @NotBlank(message = "NIK tidak boleh kosong") String nik,
                                    @NotBlank(message = "No HP tidak boleh kosong") String noHp,
                                    @NotBlank(message = "Alamat tidak boleh kosong") String address,
                                    @NotBlank(message = "Email tidak boleh kosong") String email,
                                    Status status,
                                    Role role) {
}
