package com.pokedexplus.poke_dex_plus_backend.controller;

import com.pokedexplus.poke_dex_plus_backend.dto.AuthRequest;
import com.pokedexplus.poke_dex_plus_backend.dto.AuthResponse;
import com.pokedexplus.poke_dex_plus_backend.dto.RegisterDTO;
import com.pokedexplus.poke_dex_plus_backend.repository.UserRepository;
import com.pokedexplus.poke_dex_plus_backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.register(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
