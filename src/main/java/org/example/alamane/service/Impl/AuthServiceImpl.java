package org.example.alamane.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.alamane.DTO.requestDto.LoginRequest;
import org.example.alamane.DTO.responseDTO.LoginResponse;
import org.example.alamane.entity.Utilisateur;
import org.example.alamane.repository.UtilisateurRepository;
import org.example.alamane.security.JwtUtil;
import org.example.alamane.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        String token = jwtUtil.generateToken(utilisateur.getEmail());

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setEmail(utilisateur.getEmail());
        response.setNomComplet(utilisateur.getNomComplet());
        response.setRole(utilisateur.getRole().name());
        return response;
    }
}
