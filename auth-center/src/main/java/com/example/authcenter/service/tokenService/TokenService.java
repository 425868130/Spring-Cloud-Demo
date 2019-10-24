package com.example.authcenter.service.tokenService;

import io.jsonwebtoken.Claims;

import java.util.Optional;

public interface TokenService {

    Optional<String> generateToken(Claims claims);

    Optional<Claims> parseJWT(String tokenStr);

    void inspectToken(String tokenStr, Claims claims);
}
