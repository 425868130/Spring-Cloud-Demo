package com.example.authcenter.service.tokenService;

import io.jsonwebtoken.Claims;

public interface TokenService {

    String generateToken();

    void invalidToken(String tokenStr, Claims claims);
}
