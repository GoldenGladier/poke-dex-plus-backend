package com.pokedexplus.poke_dex_plus_backend.mapper;

import com.pokedexplus.poke_dex_plus_backend.dto.PokemonDTO.EvolutionStageDTO;
import com.pokedexplus.poke_dex_plus_backend.model.EvolutionStage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EvolutionStageMapper {

    @Mapping(source = "pokemon.id", target = "pokemonId")
    @Mapping(source = "pokemon.name", target = "pokemonName")
    @Mapping(source = "pokemon.urlImage", target = "pokemonUrlImage")
    EvolutionStageDTO toDTO(EvolutionStage evolutionStage);
}
