package com.pokedexplus.poke_dex_plus_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDTO {
    private Long id;
    private String name;
    private Integer height;
    private Integer weight;
    private String urlImage;
    private Integer baseExperience;
    private EvolutionChainDTO evolutionChain;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EvolutionChainDTO {
        private Integer id;
        private List<EvolutionStageDTO> stages;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EvolutionStageDTO {
        private Integer id;
        private Integer stageOrder;
        private Long pokemonId;
        private String pokemonName;
        private String pokemonUrlImage;
    }
}
