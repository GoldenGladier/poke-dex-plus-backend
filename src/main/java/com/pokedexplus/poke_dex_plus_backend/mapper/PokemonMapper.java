package com.pokedexplus.poke_dex_plus_backend.mapper;

import com.pokedexplus.poke_dex_plus_backend.dto.PokemonDTO;
import com.pokedexplus.poke_dex_plus_backend.model.Pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class PokemonMapper {
    public static PokemonDTO toDTO(Pokemon pokemon) {
        List<PokemonDTO.EvolutionChainDTO> chainDTOS = pokemon.getEvolutionChains().stream()
                .map(chain -> new PokemonDTO.EvolutionChainDTO(
                        chain.getId(),
                        chain.getStages().stream()
                                .map(stage -> new PokemonDTO.EvolutionStageDTO(
                                        stage.getId(),
                                        stage.getStageOrder(),
                                        stage.getPokemon().getId(),
                                        stage.getPokemon().getName()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        PokemonDTO pokemonWithEvolutionChains = new PokemonDTO(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getHeight(),
                pokemon.getWeight(),
                pokemon.getUrlImage(),
                pokemon.getBaseExperience(),
                chainDTOS
        );
        return pokemonWithEvolutionChains;
    }
}
