package com.pokedexplus.poke_dex_plus_backend.dto;

import lombok.Data;

@Data
public class PokemonSpeciesDTO {
    private EvolutionChainUrlDTO evolution_chain;

    @Data
    public static class EvolutionChainUrlDTO {
        private String url;
    }
}
