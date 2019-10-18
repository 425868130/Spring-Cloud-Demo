package com.example.authcenter.service.tokenService;

public interface TokenService {

    String generateToken();

    void invalidToken(String tokenId, String tokenStr);
}
