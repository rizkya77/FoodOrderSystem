package com.sinaukoding.finalproject.foodordersystem.model.filter;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Role;
import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;

public record CustomerFilterRecord(String name,
                                   String nik,
                                   String noHp,
                                   String email,
                                   String address,
                                   Status status,
                                   Role role) {
}
