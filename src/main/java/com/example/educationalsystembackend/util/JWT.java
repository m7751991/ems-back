package com.example.educationalsystembackend.util;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JWT {
    //生成token
    public static String getToken(String username) {
        long now = System.currentTimeMillis();//当前时间毫秒
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token;
        token = com.auth0.jwt.JWT.create()
                .withHeader(map)  // Header
                .withClaim("username", username)  // Payload
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(now + 1000 * 60 * 60 * 24 * 2)) // 过期时间2天
                .sign(Algorithm.HMAC256("!34ADAS")); //签名
        return token; //返回token
    }

    //解析token
    public static String token(String token) {
        JWTVerifier jwtVerifier = com.auth0.jwt.JWT.require(Algorithm.HMAC256("!34ADAS")).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify.getClaim("username").asString(); //返回token中的用户名
    }

    public static DecodedJWT verfiy(String token) {
        JWTVerifier jwtVerifier = com.auth0.jwt.JWT.require(Algorithm.HMAC256("!34ADAS")).build();
        DecodedJWT verify;
        verify = jwtVerifier.verify(token);
        return verify;
    }
}