package com.example;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtTest {
    @Test
    public void testCreateJwt(){
        String key = "f25578ed173d4fec8f03a907c9cf0e0b";
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));


        Date curDate = new Date();
        Map<String, Object> data = new HashMap<>();
        data.put("userId", 1001);
        data.put("name", "lisi");
        data.put("role", "manager");

        String jwt = Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(DateUtils.addMinutes(curDate, 10))
                .setIssuedAt(curDate)
                .setId(UUID.randomUUID().toString())
                .addClaims(data).compact();
        System.out.println("jwt==" + jwt);
    }

    @Test
    public void testReadJwt(){
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MTY1Mjc3MzAsImlhdCI6MTcxNjUyNzEzMCwianRpIjoiM2FkNmZmOTUtODU5My00N2MyLWJlOWMtN2FkYzY2YzMxZDA0Iiwicm9sZSI6Im1hbmFnZXIiLCJuYW1lIjoibGlzaSIsInVzZXJJZCI6MTAwMX0.3s9UO4uFE0SELaw8DbRQRjkHQFFSz88_hSyY02kZCDg";
        String key = "f25578ed173d4fec8f03a907c9cf0e0b";
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(jwt);
        Claims body = claims.getBody();
        Integer userId = body.get("userId", Integer.class);
        System.out.println("userId=" + userId);
        Object name = body.get("name");
        if (name != null) {
            String str = (String)name;
            System.out.println("str=" + str);

        }
        String jwtId = body.getId();
        System.out.println("jwtId=" + jwtId);
        Date expiration = body.getExpiration();
        System.out.println("expiration = " + expiration);


    }

}
