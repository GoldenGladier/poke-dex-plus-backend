package com.pokedexplus.poke_dex_plus_backend.repository;

import com.pokedexplus.poke_dex_plus_backend.model.ApiAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiAccessLogRepository extends JpaRepository<ApiAccessLog, Long> {
    List<ApiAccessLog> findAll();
}
