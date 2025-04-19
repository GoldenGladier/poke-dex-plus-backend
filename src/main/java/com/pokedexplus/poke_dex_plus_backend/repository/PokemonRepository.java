package com.pokedexplus.poke_dex_plus_backend.repository;

import com.pokedexplus.poke_dex_plus_backend.model.Pokemon;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
    Optional<Pokemon> findById(Long id);
    Optional<Pokemon> findByName(String name);
}
