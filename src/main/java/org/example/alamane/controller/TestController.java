package org.example.alamane.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/public")
    public Map<String, String> publicEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "✅ Endpoint public accessible sans token");
        return response;
    }

    @GetMapping("/protected")
    public Map<String, Object> protectedEndpoint() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "✅ Token valide!");
        response.put("email", auth.getName());
        response.put("authorities", auth.getAuthorities().toString());
        return response;
    }

    @GetMapping("/comptable")
    @PreAuthorize("hasRole('COMPTABLE')")
    public Map<String, String> comptableOnly() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "✅ Accès COMPTABLE autorisé");
        response.put("role", "COMPTABLE");
        return response;
    }

    @GetMapping("/societe")
    @PreAuthorize("hasRole('SOCIETE')")
    public Map<String, String> societeOnly() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "✅ Accès SOCIETE autorisé");
        response.put("role", "SOCIETE");
        return response;
    }
}
