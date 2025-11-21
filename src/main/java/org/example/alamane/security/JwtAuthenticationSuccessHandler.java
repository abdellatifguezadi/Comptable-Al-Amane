package org.example.alamane.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
                                       Authentication authentication) throws IOException {
        String token = jwtUtil.generateToken(authentication.getName());
        
        Map<String, String> tokenResponse = new HashMap<>();
        tokenResponse.put("token", token);
        tokenResponse.put("email", authentication.getName());
        
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(tokenResponse));
    }
}
