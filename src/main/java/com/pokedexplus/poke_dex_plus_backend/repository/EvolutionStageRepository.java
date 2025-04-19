package com.pokedexplus.poke_dex_plus_backend.repository;

import com.pokedexplus.poke_dex_plus_backend.model.EvolutionStage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvolutionStageRepository extends JpaRepository<EvolutionStage, Integer> {
}
