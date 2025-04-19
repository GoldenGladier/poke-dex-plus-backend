package com.pokedexplus.poke_dex_plus_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExternalPokemonDTO {
    private int id;
    private String name;
    private int height;
    private int weight;

    private Sprites sprites;

    @JsonProperty("base_experience")
    private int baseExperience;

    @Data
    public static class Sprites {
        @JsonProperty("front_default")
        private String frontDefault;
    }
}
