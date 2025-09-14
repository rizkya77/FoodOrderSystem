package com.sinaukoding.finalproject.foodordersystem.mapper;

import com.sinaukoding.finalproject.foodordersystem.entity.Admin;
import com.sinaukoding.finalproject.foodordersystem.model.request.AdminRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public Admin requestToEntity(AdminRequestRecord request){
        return Admin.builder()
                .name(request.name().toUpperCase())
                .username(request.username().toLowerCase())
                .email(request.email().toLowerCase())
                .password(request.password())
                .status(request.status())
                .role(request.role())
                .build();
    }
}
