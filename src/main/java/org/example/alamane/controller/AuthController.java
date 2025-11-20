package org.example.alamane.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.alamane.DTO.requestDto.LoginRequest;
import org.example.alamane.DTO.responseDTO.LoginResponse;
import org.example.alamane.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authServiceImpl.login(request));
    }
}
