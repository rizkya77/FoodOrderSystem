package com.sinaukoding.finalproject.foodordersystem.service.impl;

import com.sinaukoding.finalproject.foodordersystem.builder.CustomBuilder;
import com.sinaukoding.finalproject.foodordersystem.entity.Food;
import com.sinaukoding.finalproject.foodordersystem.entity.FoodImage;
import com.sinaukoding.finalproject.foodordersystem.mapper.FoodMapper;
import com.sinaukoding.finalproject.foodordersystem.model.app.AppPage;
import com.sinaukoding.finalproject.foodordersystem.model.app.SimpleMap;
import com.sinaukoding.finalproject.foodordersystem.model.filter.FoodFilterRecord;
import com.sinaukoding.finalproject.foodordersystem.model.request.FoodRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.repository.FoodRepository;
import com.sinaukoding.finalproject.foodordersystem.service.FoodService;
import com.sinaukoding.finalproject.foodordersystem.service.app.ValidatorService;
import com.sinaukoding.finalproject.foodordersystem.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    private final ValidatorService validatorService;

    @Override
    public void add(FoodRequestRecord request) {

        validatorService.validator(request);

        if (foodRepository.existsByName(request.name().toLowerCase())) {
            throw new RuntimeException("Nama [" + request.name() + "] sudah digunakan");
        }

        var food = foodMapper.requestToEntity(request);
        foodRepository.save(food);

    }

    @Override
    public void edit(FoodRequestRecord request) {

        validatorService.validator(request);

        var userExisting = foodRepository.findById(request.id()).orElseThrow(() ->  new RuntimeException("Data makanan tidak ditemukan"));

        if (foodRepository.existsByNameAndIdNot(request.name().toLowerCase(), request.id())) {
            throw new RuntimeException("Nama [" + request.name() + "] sudah digunakan");
        }

        var food = foodMapper.requestToEntity(request);
        food.setId(userExisting.getId());
        foodRepository.save(food);
    }

    @Override
    public Page<SimpleMap> findAll(FoodFilterRecord filterRequest, Pageable pageable) {
        return null;
        /*
        CustomBuilder<Food> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("name", filterRequest.name(), builder);
        FilterUtil.builderConditionNotBlankLike("description", filterRequest.description(), builder);
        FilterUtil.builderConditionNotBlankLike("price", filterRequest.price(), builder);
        FilterUtil.builderConditionNotBlankLike("stock", filterRequest.stock(), builder);
        FilterUtil.builderConditionNotNullEqual("status", filterRequest.status(), builder);

        Page<Food> listFood = foodRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listFood.stream().map(food -> {
            SimpleMap data = new SimpleMap();
            data.put("id", food.getId());
            data.put("name", food.getName());
            data.put("description", food.getDescription());
            data.put("price", food.getPrice());
            data.put("stock", food.getStock());
            data.put("status", food.getStatus().getLabel());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listFood.getTotalElements());

         */
    }

    @Override
    public SimpleMap findById(String id) {
        var food = foodRepository.findById(id).orElseThrow(() ->  new RuntimeException("Data makanan tidak ditemukan"));
        SimpleMap data = new SimpleMap();
        data.put("id", food.getId());
        data.put("name", food.getName());
        data.put("description", food.getDescription());
        data.put("price", food.getPrice());
        data.put("stock", food.getStock());
        data.put("status", food.getStatus().getLabel());
        data.put("listImage", food.getListImage().stream().map(FoodImage::getPath).collect(Collectors.toSet()));
        return data;
    }
}
