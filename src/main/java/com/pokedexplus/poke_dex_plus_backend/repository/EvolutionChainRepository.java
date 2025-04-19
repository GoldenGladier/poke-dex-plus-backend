package com.pokedexplus.poke_dex_plus_backend.repository;

import com.pokedexplus.poke_dex_plus_backend.model.EvolutionChain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvolutionChainRepository extends JpaRepository<EvolutionChain, Long>{
}