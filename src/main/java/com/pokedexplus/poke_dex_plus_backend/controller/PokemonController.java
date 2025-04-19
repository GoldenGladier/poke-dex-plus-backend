package com.pokedexplus.poke_dex_plus_backend.controller;

import com.pokedexplus.poke_dex_plus_backend.dto.PokemonDTO;
import com.pokedexplus.poke_dex_plus_backend.mapper.PokemonMapper;
import com.pokedexplus.poke_dex_plus_backend.model.Pokemon;
import com.pokedexplus.poke_dex_plus_backend.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokedex")
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonService pokemonService;
    private final PokemonMapper pokemonMapper;

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDTO> getPokemon(@PathVariable String id) {
        Pokemon pokemon = pokemonService.findOrFetchPokemon(id);
        return ResponseEntity.ok(pokemonMapper.toDTO(pokemon));
    }
}
