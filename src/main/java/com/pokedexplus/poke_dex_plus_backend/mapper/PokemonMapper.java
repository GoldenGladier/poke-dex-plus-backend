package com.pokedexplus.poke_dex_plus_backend.mapper;

import com.pokedexplus.poke_dex_plus_backend.dto.PokemonDTO;
import com.pokedexplus.poke_dex_plus_backend.model.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EvolutionChainMapper.class)
public interface PokemonMapper {
    @Mapping(target = "evolutionChain", source = "evolutionChain")
    PokemonDTO toDTO(Pokemon pokemon);

    Pokemon toEntity(PokemonDTO dto);
}
