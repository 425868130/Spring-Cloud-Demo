package com.example.authcenter.service.tokenService;

import io.jsonwebtoken.Claims;

public interface TokenService {

    String generateToken(Claims claims);

    void inspectToken(String tokenStr, Claims claims);
}
