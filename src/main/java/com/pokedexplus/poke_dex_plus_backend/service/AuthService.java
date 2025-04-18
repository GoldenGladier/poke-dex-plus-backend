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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            log.error("Username '{}' already exists", registerDTO.getUsername());
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        User user = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .build();

        userRepository.save(user);
        log.info("'{}' user registered successfully", user.getUsername());
        String token = jwtService.generateToken(user.getUsername());
        log.debug("User generated token '{}': '{}'", user.getUsername(), token);
        return new AuthResponse(token);
    }

    public AuthResponse authenticate(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> {
                    log.error("Login failed: username '{}' not found", request.getUsername());
                    return new InvalidCredentialsException("Invalid username or password");
                });

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.error("Login failed: incorrect password for username '{}'", request.getUsername());
            throw new InvalidCredentialsException("Invalid username or password");
        }

        String token = jwtService.generateToken(user.getUsername());
        log.info("User '{}' authenticated successfully", user.getUsername());
        log.debug("User generated token '{}': {}", user.getUsername(), token);

        return new AuthResponse(token);
    }
}
