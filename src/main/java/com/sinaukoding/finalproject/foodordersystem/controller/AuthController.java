package com.sinaukoding.finalproject.foodordersystem.controller;

import com.sinaukoding.finalproject.foodordersystem.config.UserLoggedInConfig;
import com.sinaukoding.finalproject.foodordersystem.model.request.LoginRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.model.response.BaseResponse;
import com.sinaukoding.finalproject.foodordersystem.service.app.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public BaseResponse<?> login(@RequestBody LoginRequestRecord request) {
        return BaseResponse.ok(null, authService.login(request));
    }

    @GetMapping("logout")
    public BaseResponse<?> logout(@AuthenticationPrincipal UserLoggedInConfig userLoggedInConfig) {
        var userLoggedIn = userLoggedInConfig.getAdmin();
        authService.logout(userLoggedIn);
        return BaseResponse.ok("Berhasil logout", null);
    }

}
