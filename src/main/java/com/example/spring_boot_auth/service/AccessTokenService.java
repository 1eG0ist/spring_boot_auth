package com.example.spring_boot_auth.service;

import com.example.spring_boot_auth.config.MyUserDetails;

import java.util.List;

public interface AccessTokenService {
    String generateToken(MyUserDetails userDetails);
    String getUserEmail(String token);
    String getUserId(String token);
    List<String> getRoles(String token);
}
