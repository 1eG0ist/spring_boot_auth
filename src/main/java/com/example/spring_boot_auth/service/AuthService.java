package com.example.spring_boot_auth.service;

import com.example.spring_boot_auth.dtos.AuthRequestDto;
import com.example.spring_boot_auth.dtos.RefreshTokenRequestDto;
import com.example.spring_boot_auth.dtos.RegistrationUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface AuthService {
    ResponseEntity<?> authTokensForLogin(@RequestBody AuthRequestDto authRequest);
    ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto);
    ResponseEntity<?> createTokensByRefreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO);
}
