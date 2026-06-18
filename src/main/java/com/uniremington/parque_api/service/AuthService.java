package com.uniremington.parque_api.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniremington.parque_api.dto.LoginRequest;
import com.uniremington.parque_api.dto.LoginResponse;
import com.uniremington.parque_api.entity.Usuario;
import com.uniremington.parque_api.repository.UsuarioRepository;
import com.uniremington.parque_api.security.JwtUtil;

@Service
public class AuthService {

    private final UsuarioRepository repo;

    private final JwtUtil jwt;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository repo, JwtUtil jwt, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.jwt = jwt;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {

        Usuario user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }

        String token = jwt.generateToken(
                user.getEmail(),
                user.getRol().getNombre()
        );

        return new LoginResponse(token);
    }
}