package com.sinaukoding.finalproject.foodordersystem;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Role;
import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;
import com.sinaukoding.finalproject.foodordersystem.model.request.AdminRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    void addAdminTest() {
        AdminRequestRecord request = new AdminRequestRecord(null,
                "Rizky",
                "rizky",
                "rizky@email.com",
                "rizky123",
                Status.AKTIF,
                Role.ADMIN
        );
        adminService.add(request);
    }
}
