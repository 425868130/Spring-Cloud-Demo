package com.example.common.util.jwt;

import io.jsonwebtoken.SignatureAlgorithm;

public interface JwtUtil {
     SignatureAlgorithm getSignatureAlgorithm();
}
