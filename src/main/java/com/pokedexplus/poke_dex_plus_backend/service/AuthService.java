package com.pokedexplus.poke_dex_plus_backend.service;

import com.pokedexplus.poke_dex_plus_backend.dto.AuthRequest;
import com.pokedexplus.poke_dex_plus_backend.dto.AuthResponse;
import com.pokedexplus.poke_dex_plus_backend.dto.RegisterDTO;
import com.pokedexplus.poke_dex_plus_backend.exception.InvalidCredentialsException;
import com.pokedexplus.poke_dex_plus_backend.exception.UsernameAlreadyExistsException;
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

    public AuthResponse register(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        User user = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

    public AuthResponse authenticate(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
