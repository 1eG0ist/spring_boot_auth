package com.example.spring_boot_auth.service;
import com.example.spring_boot_auth.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String email);
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> verifyExpiration(RefreshToken token);
}
