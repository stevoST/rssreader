package com.domain.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

    private final String SECRET = "some secret key, write something meaningful here";

    public String generateToken(LoginDetails loginDetails){
        return Jwts.builder()
                .setIssuer(loginDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
    }
}
