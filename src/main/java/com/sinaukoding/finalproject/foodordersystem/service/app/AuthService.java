package com.sinaukoding.finalproject.foodordersystem.service.app;

import com.sinaukoding.finalproject.foodordersystem.entity.Admin;
import com.sinaukoding.finalproject.foodordersystem.model.app.SimpleMap;
import com.sinaukoding.finalproject.foodordersystem.model.request.LoginRequestRecord;

public interface AuthService {

    SimpleMap login(LoginRequestRecord request);

    void logout(Admin userLoggedIn);

}
