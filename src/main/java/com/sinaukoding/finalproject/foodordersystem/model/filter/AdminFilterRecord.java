package com.sinaukoding.finalproject.foodordersystem.model.filter;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Role;
import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;

public record AdminFilterRecord(String name,
                                String email,
                                String username,
                                Status status,
                                Role role) {
}
