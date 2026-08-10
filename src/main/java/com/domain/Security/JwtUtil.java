package com.domain.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtUtil {

    private final String SECRET = "someSecretKeyWriteSomethingMeaningfulHereAndLongEnoughToBeSecure";
    private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));

    public String generateToken(LoginDetails loginDetails){
        return Jwts.builder()
                .issuer(loginDetails.getUsername())
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }
}
