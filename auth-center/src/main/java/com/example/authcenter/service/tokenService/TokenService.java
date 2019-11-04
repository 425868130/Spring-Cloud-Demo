package com.example.authcenter.service.tokenService;

import com.example.common.define.jwt.JwtPayload;

import java.util.Optional;

public interface TokenService {

    Optional<String> generateToken(JwtPayload jwtPayload);

    Optional<JwtPayload> parseJWT(String tokenStr);

    void inspectToken(String tokenStr, JwtPayload jwtPayload);
}
