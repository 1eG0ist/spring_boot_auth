package com.example.spring_boot_auth.service.Impl;

import com.example.spring_boot_auth.config.MyUserDetails;
import com.example.spring_boot_auth.dtos.*;
import com.example.spring_boot_auth.exceptions.AppError;
import com.example.spring_boot_auth.model.RefreshToken;
import com.example.spring_boot_auth.model.User;
import com.example.spring_boot_auth.service.AccessTokenService;
import com.example.spring_boot_auth.service.AuthService;
import com.example.spring_boot_auth.service.RefreshTokenService;
import com.example.spring_boot_auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> authTokensForLogin(@RequestBody AuthRequestDto authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            // В случае ошибки аутентификации, отправка сообщения об ошибке в ответе
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Wrong login or password"), HttpStatus.UNAUTHORIZED);
        }
        return getTokens(authRequest.getEmail());
    }

    public ResponseEntity<?> createTokensByRefreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getRefreshToken())
                .flatMap(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = accessTokenService.generateToken(userService.findByEmail(userInfo.getEmail()));
                    return ResponseEntity.ok(JwtResponseDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequestDTO.getRefreshToken()).build());
                }).orElse(ResponseEntity.badRequest().build());
    }

    public ResponseEntity<?> getTokens(String email) {
        MyUserDetails userDetails = userService.findByEmail(email);
        String accessToken = accessTokenService.generateToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(email);
        return ResponseEntity.ok(JwtResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .build());
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (userService.findByEmail(registrationUserDto.getEmail()) != null) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "User with this email is all ready exist"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(registrationUserDto);
        return getTokens(user.getEmail());
    }
}
