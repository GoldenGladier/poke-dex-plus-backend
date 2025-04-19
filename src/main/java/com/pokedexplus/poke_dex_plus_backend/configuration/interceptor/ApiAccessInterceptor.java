package com.pokedexplus.poke_dex_plus_backend.configuration.interceptor;

import com.pokedexplus.poke_dex_plus_backend.model.ApiAccessLog;
import com.pokedexplus.poke_dex_plus_backend.model.Pokemon;
import com.pokedexplus.poke_dex_plus_backend.model.User;
import com.pokedexplus.poke_dex_plus_backend.repository.ApiAccessLogRepository;
import com.pokedexplus.poke_dex_plus_backend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class ApiAccessInterceptor implements HandlerInterceptor {

    @Autowired
    private ApiAccessLogRepository logRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info(">> Interceptor ejecutado para " + request.getRequestURI());

        String endpoint = request.getRequestURI();
        String method = request.getMethod();
        int status = response.getStatus();
        String username = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "anonymous";

        User user = userRepository.findByUsername(username).orElse(null);


        String pokemonIdStr = null;
        if (endpoint.matches("/pokedex/\\d+")) {
            pokemonIdStr = endpoint.split("/")[2];
        } else if (endpoint.matches("/pokedex/without-evolution/\\d+")) {
            pokemonIdStr = endpoint.split("/")[3];  // Para el nuevo endpoint, el id está en la posición 3
        }

        Pokemon pokemon = null;
        if (pokemonIdStr != null) {
            try {
                Long pokemonId = Long.parseLong(pokemonIdStr);
                pokemon = new Pokemon();
                pokemon.setId(pokemonId);
            } catch (NumberFormatException ignored) {}
        }

        ApiAccessLog log = ApiAccessLog.builder()
                .endpointAccessed(endpoint)
                .action(method)
                .statusCode(status)
                .user(user)
                .pokemon(pokemon)
                .build();

        logRepository.save(log);
    }
}