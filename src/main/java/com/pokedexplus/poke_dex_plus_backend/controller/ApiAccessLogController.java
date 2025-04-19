package com.pokedexplus.poke_dex_plus_backend.controller;

import com.pokedexplus.poke_dex_plus_backend.dto.ApiAccessLogDTO;
import com.pokedexplus.poke_dex_plus_backend.service.ApiAccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/access-logs")
@RequiredArgsConstructor
public class ApiAccessLogController {
    private final ApiAccessLogService apiAccessLogService;

    @GetMapping
    public ResponseEntity<List<ApiAccessLogDTO>> getAllApiAccessLogs() {
        List<ApiAccessLogDTO> logs = apiAccessLogService.getAllApiAccessLogs();
        return ResponseEntity.ok(logs);
    }
}
