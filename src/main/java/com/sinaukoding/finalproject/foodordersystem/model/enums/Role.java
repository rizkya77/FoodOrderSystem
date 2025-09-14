package com.sinaukoding.finalproject.foodordersystem.model.enums;

import lombok.Getter;

@Getter
public enum Role {

    CUSTOMER("Customer"),
    ADMIN("Admin");

    private final String label;

    Role(String label) {
        this.label = label;
    }

}
