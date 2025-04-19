package com.pokedexplus.poke_dex_plus_backend.service;

import com.pokedexplus.poke_dex_plus_backend.dto.ApiAccessLogDTO;
import com.pokedexplus.poke_dex_plus_backend.model.ApiAccessLog;
import com.pokedexplus.poke_dex_plus_backend.repository.ApiAccessLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiAccessLogService {
    private final ApiAccessLogRepository apiAccessLogRepository;

    public List<ApiAccessLogDTO> getAllApiAccessLogs() {
        List<ApiAccessLog> logs = apiAccessLogRepository.findAll();
        return logs.stream()
                .map(ApiAccessLogDTO::new)
                .collect(Collectors.toList());
    }
}
