package com.sinaukoding.finalproject.foodordersystem.service.app.impl;

import com.sinaukoding.finalproject.foodordersystem.entity.Admin;
import com.sinaukoding.finalproject.foodordersystem.model.app.SimpleMap;
import com.sinaukoding.finalproject.foodordersystem.model.request.LoginRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.repository.AdminRepository;
import com.sinaukoding.finalproject.foodordersystem.service.app.AuthService;
import com.sinaukoding.finalproject.foodordersystem.service.app.ValidatorService;
import com.sinaukoding.finalproject.foodordersystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ValidatorService validatorService;

    @Override
    public SimpleMap login(LoginRequestRecord request) {
        validatorService.validator(request);
        var user = adminRepository.findByUsername(request.username().toLowerCase()).orElseThrow(() -> new RuntimeException("Username atau password salah"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Username atau password salah");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        user.setToken(token);
        user.setExpiredTokenAt(LocalDateTime.now().plusHours(1));
        adminRepository.save(user);
        SimpleMap result = new SimpleMap();
        result.put("token", token);
        return result;
    }

    @Override
    public void logout(Admin userLoggedIn) {
        userLoggedIn.setToken(null);
        userLoggedIn.setExpiredTokenAt(null);
        adminRepository.save(userLoggedIn);
    }

}
