package com.example.practiceSecurity.Config;

import com.example.practiceSecurity.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {


    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getUsername().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*10*60))
                .signWith(getSecretKey())
                .compact();
    }

    public String getUserNameFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
