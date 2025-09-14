package com.sinaukoding.finalproject.foodordersystem.mapper;

import com.sinaukoding.finalproject.foodordersystem.entity.Customer;
import com.sinaukoding.finalproject.foodordersystem.model.request.CustomerRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer requestToEntity(CustomerRequestRecord request){
        return Customer.builder()
                .name(request.name().toUpperCase())
                .nik(request.nik())
                .noHp(request.noHp())
                .email(request.email().toLowerCase())
                .address(request.address())
                .status(request.status())
                .role(request.role())
                .build();
    }
}
