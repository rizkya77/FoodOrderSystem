package com.sinaukoding.finalproject.foodordersystem.service.app.impl;

import com.sinaukoding.finalproject.foodordersystem.config.UserLoggedInConfig;
import com.sinaukoding.finalproject.foodordersystem.model.app.Checks;
import com.sinaukoding.finalproject.foodordersystem.repository.AdminRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoggedInServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin : " + username + " tidak ditemukan"));
        Checks.isTrue(StringUtils.isNotBlank(user.getToken()), "Session habis, silahkan login kembali");
        return new UserLoggedInConfig(user);
    }

}
