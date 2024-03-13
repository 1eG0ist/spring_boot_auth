package com.example.spring_boot_auth.controller;

import com.example.spring_boot_auth.dtos.AuthRequestDto;
import com.example.spring_boot_auth.dtos.RefreshTokenRequestDto;
import com.example.spring_boot_auth.dtos.RegistrationUserDto;
import com.example.spring_boot_auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authTokensForLogin(@RequestBody AuthRequestDto authRequest) {
        return authService.authTokensForLogin(authRequest);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> createTokensByRefreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO){
        return authService.createTokensByRefreshToken(refreshTokenRequestDTO);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}


