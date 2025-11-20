package org.example.alamane.service;

import org.example.alamane.DTO.requestDto.LoginRequest;
import org.example.alamane.DTO.responseDTO.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
