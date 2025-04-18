package com.pokedexplus.poke_dex_plus_backend.dto;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RegisterDTO {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
}
