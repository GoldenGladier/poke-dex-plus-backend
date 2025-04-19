package com.pokedexplus.poke_dex_plus_backend.dto;

import com.pokedexplus.poke_dex_plus_backend.model.ApiAccessLog;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiAccessLogDTO {
    private Long id;
    private String username;
    private String pokemonName;
    private String endpointAccessed;
    private LocalDateTime accessTime;
    private int statusCode;
    private String action;

    public ApiAccessLogDTO(ApiAccessLog log) {
        this.id = log.getId();
        this.username = log.getUser() != null ? log.getUser().getUsername() : "anonymous";
        this.pokemonName = log.getPokemon() != null ? log.getPokemon().getName() : "unknown";
        this.endpointAccessed = log.getEndpointAccessed();
        this.accessTime = log.getAccessTime();
        this.statusCode = log.getStatusCode();
        this.action = log.getAction();
    }
}