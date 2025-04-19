package com.pokedexplus.poke_dex_plus_backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class EvolutionChainDTO {
    private int id;
    private EvolutionLink chain;

    @Data
    public static class EvolutionLink {
        private NamedResource species;
        private List<EvolutionLink> evolves_to;
    }

    @Data
    public static class NamedResource {
        private String name;
        private String url;
    }
}
