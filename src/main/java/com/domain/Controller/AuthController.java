package com.domain.Controller;

import com.domain.Security.JwtUtil;
import com.domain.Security.LoginDetails;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/login")
    public String signIn(@RequestBody LoginDetails loginDetails){
        return jwtUtil.generateToken(loginDetails);
    }
}
