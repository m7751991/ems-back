package com.example.educationalsystembackend.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.educationalsystembackend.util.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token;
        Map<String, Object> map = new HashMap<>();
        token = request.getHeader("Authorization");
        try {
            DecodedJWT decodedJWT = JWT.verfiy(token);
            return true;
        } catch (SignatureVerificationException e) {
            map.put("msg", "无效签名");
        } catch (TokenExpiredException e) {
            map.put("msg", "token过期");
        } catch (AlgorithmMismatchException e) {
            map.put("msg", "token算法不一致");
        } catch (Exception e) {
            map.put("msg", "token无效");
        }
        map.put("code", 401);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);
        response.getWriter().println(json);
        return false;
    }
}
