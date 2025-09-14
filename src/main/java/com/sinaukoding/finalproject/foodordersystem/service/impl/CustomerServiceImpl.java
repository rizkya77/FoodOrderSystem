package com.sinaukoding.finalproject.foodordersystem.service.impl;

import com.sinaukoding.finalproject.foodordersystem.builder.CustomBuilder;
import com.sinaukoding.finalproject.foodordersystem.entity.Customer;
import com.sinaukoding.finalproject.foodordersystem.mapper.CustomerMapper;
import com.sinaukoding.finalproject.foodordersystem.model.app.AppPage;
import com.sinaukoding.finalproject.foodordersystem.model.app.SimpleMap;
import com.sinaukoding.finalproject.foodordersystem.model.filter.CustomerFilterRecord;
import com.sinaukoding.finalproject.foodordersystem.model.request.CustomerRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.repository.CustomerRepository;
import com.sinaukoding.finalproject.foodordersystem.service.CustomerService;
import com.sinaukoding.finalproject.foodordersystem.service.app.ValidatorService;
import com.sinaukoding.finalproject.foodordersystem.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ValidatorService validatorService;

    @Override
    public void add(CustomerRequestRecord request) {

        validatorService.validator(request);

        if (customerRepository.existsByEmail(request.email().toLowerCase())) {
            throw new RuntimeException("Email [" + request.email() + "] sudah digunakan");
        }

        var customer = customerMapper.requestToEntity(request);
        customerRepository.save(customer);

    }

    @Override
    public void edit(CustomerRequestRecord request) {

        validatorService.validator(request);

        var userExisting = customerRepository.findById(request.id()).orElseThrow(() ->  new RuntimeException("Data customer tidak ditemukan"));

        if (customerRepository.existsByEmailAndIdNot(request.email().toLowerCase(), request.id())) {
            throw new RuntimeException("Email [" + request.email() + "] sudah digunakan");
        }

        var customer = customerMapper.requestToEntity(request);
        customer.setId(userExisting.getId());
        customerRepository.save(customer);

    }

    @Override
    public Page<SimpleMap> findAll(CustomerFilterRecord filterRequest, Pageable pageable) {
        return null;
        /*
        CustomBuilder<Customer> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("name", filterRequest.name(), builder);
        FilterUtil.builderConditionNotBlankLike("nik", filterRequest.nik(), builder);
        FilterUtil.builderConditionNotBlankLike("noHp", filterRequest.noHp(), builder);
        FilterUtil.builderConditionNotBlankLike("address", filterRequest.address(), builder);
        FilterUtil.builderConditionNotBlankLike("email", filterRequest.email(), builder);
        FilterUtil.builderConditionNotNullEqual("status", filterRequest.status(), builder);
        FilterUtil.builderConditionNotNullEqual("role", filterRequest.role(), builder);

        Page<Customer> listCustomer = customerRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listCustomer.stream().map(customer -> {
            SimpleMap data = new SimpleMap();
            data.put("id", customer.getId());
            data.put("name", customer.getName());
            data.put("nik", customer.getNik());
            data.put("noHp", customer.getNoHp());
            data.put("address", customer.getAddress());
            data.put("email", customer.getEmail());
            data.put("role", customer.getStatus().getLabel());
            data.put("status", customer.getRole().getLabel());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listCustomer.getTotalElements());

         */
    }

    @Override
    public SimpleMap findById(String id) {
        var customer = customerRepository.findById(id).orElseThrow(() ->  new RuntimeException("Data customer tidak ditemukan"));
        SimpleMap data = new SimpleMap();
        data.put("id", customer.getId());
        data.put("name", customer.getName());
        data.put("nik", customer.getNik());
        data.put("noHp", customer.getNoHp());
        data.put("address", customer.getAddress());
        data.put("email", customer.getEmail());
        data.put("status", customer.getStatus().getLabel());
        data.put("role", customer.getRole().getLabel());
        return data;
    }
}
