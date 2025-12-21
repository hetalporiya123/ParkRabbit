package com.parkrabbit.backend.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.io.Decoders;
import java.security.Key;
import java.util.Date; // <--- MAKE SURE IT IS UTIL, NOT SQL

@Component
public class JwtUtils {
    // Note: Use a more secure key in production
    private final String jwtSecret = "CybpGI++/WsqSRqmpgSl658a0PuxCL2CQK5Tuc7WaEo=";
    private final int jwtExpirationMs = 86400000;
    private final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date()) // Now this will work!
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}