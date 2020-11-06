package com.domain.Controller;

import com.domain.Security.JwtUtil;
import com.domain.Security.LoginDetails;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/login")
    public String signIn(@RequestBody LoginDetails loginDetails){
        return jwtUtil.generateToken(loginDetails);
    }
}
