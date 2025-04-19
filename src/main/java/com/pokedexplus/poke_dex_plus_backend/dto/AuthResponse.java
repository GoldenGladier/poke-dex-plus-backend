package com.pokedexplus.poke_dex_plus_backend.dto;

import com.pokedexplus.poke_dex_plus_backend.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String username;
    private Role role;
}
