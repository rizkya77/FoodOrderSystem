package com.sinaukoding.finalproject.foodordersystem;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;
import com.sinaukoding.finalproject.foodordersystem.model.request.FoodRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.service.FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FoodServiceTest {

    @Autowired
    private FoodService foodService;

    @Test
    void addFoodTest() {
        FoodRequestRecord request = new FoodRequestRecord(null,
                "Rizky",
                "rizky",
                1000.00,
                5,
                Status.AKTIF
        );
        foodService.add(request);
    }
}
