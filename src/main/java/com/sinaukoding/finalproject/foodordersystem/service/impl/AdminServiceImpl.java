package com.sinaukoding.finalproject.foodordersystem.service.impl;

import com.sinaukoding.finalproject.foodordersystem.builder.CustomBuilder;
import com.sinaukoding.finalproject.foodordersystem.entity.Admin;
import com.sinaukoding.finalproject.foodordersystem.mapper.AdminMapper;
import com.sinaukoding.finalproject.foodordersystem.model.app.AppPage;
import com.sinaukoding.finalproject.foodordersystem.model.app.SimpleMap;
import com.sinaukoding.finalproject.foodordersystem.model.filter.AdminFilterRecord;
import com.sinaukoding.finalproject.foodordersystem.model.request.AdminRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.repository.AdminRepository;
import com.sinaukoding.finalproject.foodordersystem.service.AdminService;
import com.sinaukoding.finalproject.foodordersystem.service.app.ValidatorService;
import com.sinaukoding.finalproject.foodordersystem.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final ValidatorService validatorService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void add(AdminRequestRecord request) {

        validatorService.validator(request);

        if (adminRepository.existsByEmail(request.email().toLowerCase())) {
            throw new RuntimeException("Email [" + request.email() + "] sudah digunakan");
        }
        if (adminRepository.existsByUsername(request.username().toLowerCase())) {
            throw new RuntimeException("Username [" + request.username() + "] sudah digunakan");
        }

        var admin = adminMapper.requestToEntity(request);
        admin.setPassword(passwordEncoder.encode(request.password()));
        adminRepository.save(admin);

    }

    @Override
    public void edit(AdminRequestRecord request) {

        validatorService.validator(request);

        var userExisting = adminRepository.findById(request.id()).orElseThrow(() ->  new RuntimeException("Data admin tidak ditemukan"));

        if (adminRepository.existsByEmailAndIdNot(request.email().toLowerCase(), request.id())) {
            throw new RuntimeException("Email [" + request.email() + "] sudah digunakan");
        }
        if (adminRepository.existsByUsernameAndIdNot(request.username().toLowerCase(),  request.id())) {
            throw new RuntimeException("Username [" + request.username() + "] sudah digunakan");
        }

        var admin = adminMapper.requestToEntity(request);
        admin.setId(userExisting.getId());
        admin.setPassword(passwordEncoder.encode(request.password()));
        adminRepository.save(admin);
    }

    @Override
    public Page<SimpleMap> findAll(AdminFilterRecord filterRequest, Pageable pageable) {
        /*
        CustomBuilder<Admin> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("nama", filterRequest.name(), builder);
        FilterUtil.builderConditionNotBlankLike("email", filterRequest.email(), builder);
        FilterUtil.builderConditionNotBlankLike("username", filterRequest.username(), builder);
        FilterUtil.builderConditionNotNullEqual("status", filterRequest.status(), builder);
        FilterUtil.builderConditionNotNullEqual("role", filterRequest.role(), builder);

        Page<Admin> listAdmin = adminRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listAdmin.stream().map(admin -> {
            SimpleMap data = new SimpleMap();
            data.put("id", admin.getId());
            data.put("nama", admin.getName());
            data.put("username", admin.getUsername());
            data.put("email", admin.getEmail());
            data.put("role", admin.getStatus().getLabel());
            data.put("status", admin.getRole().getLabel());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listAdmin.getTotalElements());

         */
        return null;
    }

    @Override
    public SimpleMap findById(String id) {
        var admin = adminRepository.findById(id).orElseThrow(() ->  new RuntimeException("Data admin tidak ditemukan"));
        SimpleMap data = new SimpleMap();
        data.put("id", admin.getId());
        data.put("name", admin.getName());
        data.put("username", admin.getUsername());
        data.put("email", admin.getEmail());
        data.put("status", admin.getStatus().getLabel());
        data.put("role", admin.getRole().getLabel());
        return data;
    }
}
