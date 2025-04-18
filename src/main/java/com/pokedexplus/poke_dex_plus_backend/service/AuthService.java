package com.pokedexplus.poke_dex_plus_backend.service;

import com.pokedexplus.poke_dex_plus_backend.dto.AuthRequest;
import com.pokedexplus.poke_dex_plus_backend.dto.AuthResponse;
import com.pokedexplus.poke_dex_plus_backend.dto.RegisterRequest;
import com.pokedexplus.poke_dex_plus_backend.model.User;
import com.pokedexplus.poke_dex_plus_backend.repository.UserRepository;
import com.pokedexplus.poke_dex_plus_backend.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.username)
                .password(passwordEncoder.encode(request.password))
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

    public AuthResponse authenticate(AuthRequest request) {
        User user = userRepository.findByUsername(request.username).orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
