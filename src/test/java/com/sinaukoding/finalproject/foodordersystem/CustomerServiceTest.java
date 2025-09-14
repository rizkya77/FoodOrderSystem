package com.sinaukoding.finalproject.foodordersystem;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Role;
import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;
import com.sinaukoding.finalproject.foodordersystem.model.request.CustomerRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void addCustomerTest() {
        CustomerRequestRecord request = new CustomerRequestRecord(null,
                "Rizky",
                "3273083273080001",
                "082108210821",
                "Jakarta",
                "rizky@email.com",
                Status.AKTIF,
                Role.CUSTOMER
        );
        customerService.add(request);
    }
}
