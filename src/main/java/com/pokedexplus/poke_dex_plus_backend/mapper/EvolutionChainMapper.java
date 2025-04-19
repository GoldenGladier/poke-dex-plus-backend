package com.pokedexplus.poke_dex_plus_backend.mapper;

import com.pokedexplus.poke_dex_plus_backend.dto.PokemonDTO.EvolutionChainDTO;
import com.pokedexplus.poke_dex_plus_backend.dto.PokemonDTO.EvolutionStageDTO;
import com.pokedexplus.poke_dex_plus_backend.model.EvolutionChain;
import com.pokedexplus.poke_dex_plus_backend.model.EvolutionStage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = EvolutionStageMapper.class)
public interface EvolutionChainMapper {
    EvolutionChainDTO toDTO(EvolutionChain evolutionChain);
    List<EvolutionStageDTO> toStageDTOs(List<EvolutionStage> evolutionStages);
}
