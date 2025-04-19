package com.pokedexplus.poke_dex_plus_backend.service;

import com.pokedexplus.poke_dex_plus_backend.dto.EvolutionChainDTO;
import com.pokedexplus.poke_dex_plus_backend.dto.ExternalPokemonDTO;
import com.pokedexplus.poke_dex_plus_backend.dto.PokemonSpeciesDTO;
import com.pokedexplus.poke_dex_plus_backend.exception.InvalidPokemonIdException;
import com.pokedexplus.poke_dex_plus_backend.model.EvolutionChain;
import com.pokedexplus.poke_dex_plus_backend.model.EvolutionStage;
import com.pokedexplus.poke_dex_plus_backend.model.Pokemon;
import com.pokedexplus.poke_dex_plus_backend.repository.EvolutionChainRepository;
import com.pokedexplus.poke_dex_plus_backend.repository.EvolutionStageRepository;
import com.pokedexplus.poke_dex_plus_backend.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PokemonService {
    private final PokemonRepository pokemonRepository;
    private final EvolutionChainRepository evolutionChainRepository;
    private final EvolutionStageRepository evolutionStageRepository;
    private final RestTemplate restTemplate;

    public Pokemon findOrFetchPokemon(String id) {
        Long pokemonId;
        try {
            pokemonId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            log.error("The Pokemon ID must be a number: '{}'", id);
            throw new InvalidPokemonIdException("The Pokemon ID must be a number");
        }

        log.debug("Looking for the ID '{}' Pokemon", id);
        Optional<Pokemon> pokemon = pokemonRepository.findById(pokemonId);
        if(pokemon.isPresent()) return pokemon.get();

        // 1. If the Pokemon doesn't exist in DB
        log.info("Pokemon ID '{}' does not exist, searching on a central base", id);
        String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemonId;
        ExternalPokemonDTO pokemonResponse = restTemplate.getForObject(pokemonUrl, ExternalPokemonDTO.class);

        Pokemon newPokemon = Pokemon.builder()
                .id((long) pokemonResponse.getId())
                .name(pokemonResponse.getName())
                .height(pokemonResponse.getHeight())
                .weight(pokemonResponse.getWeight())
                .urlImage(pokemonResponse.getSprites().getFrontDefault())
                .baseExperience(pokemonResponse.getBaseExperience())
                .build();

        newPokemon = pokemonRepository.save(newPokemon);

        // 2. Looking for the specie for the id of evolution chain
        String speciesUrl = "https://pokeapi.co/api/v2/pokemon-species/" + pokemonId;
        PokemonSpeciesDTO species = restTemplate.getForObject(speciesUrl, PokemonSpeciesDTO.class);

        if(species != null && species.getEvolution_chain().getUrl() != null) {
            String evolutionUrl = species.getEvolution_chain().getUrl();
            EvolutionChainDTO evolutionData = restTemplate.getForObject(evolutionUrl, EvolutionChainDTO.class);

            if(evolutionData != null) {
                EvolutionChain evolutionChain = EvolutionChain.builder()
                        .id(evolutionData.getId())
                        .pokemon(newPokemon)
                        .build();
                evolutionChain = evolutionChainRepository.save(evolutionChain);

                mapEvolutionStates(evolutionChain, evolutionData.getChain(), 1);
            }
        }

        return newPokemon;
    }

    private void mapEvolutionStates(EvolutionChain chainEntity, EvolutionChainDTO.EvolutionLink link, int stageOrder) {
        String name = link.getSpecies().getName();
        Optional<Pokemon> optionalPokemon = pokemonRepository.findByName(name);
        Pokemon pokemon = optionalPokemon.orElseGet(() -> fetchAndSavePokemon(name));

        EvolutionStage stage = EvolutionStage.builder()
                .evolutionChain(chainEntity)
                .stageOrder(stageOrder)
                .pokemon(pokemon)
                .build();

        evolutionStageRepository.save(stage);

        for (EvolutionChainDTO.EvolutionLink next : link.getEvolves_to()) {
            mapEvolutionStates(chainEntity, next, stageOrder + 1);
        }
    }

    private Pokemon fetchAndSavePokemon(String name) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + name;
        ExternalPokemonDTO dto = restTemplate.getForObject(url, ExternalPokemonDTO.class);

        if (dto == null) return null;

        return pokemonRepository.save(Pokemon.builder()
                .id((long) dto.getId())
                .name(dto.getName())
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .baseExperience(dto.getBaseExperience())
                .urlImage(dto.getSprites().getFrontDefault())
                .build());
    }
}
