package com.pokedexplus.poke_dex_plus_backend.configuration;

import com.pokedexplus.poke_dex_plus_backend.configuration.interceptor.ApiAccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private ApiAccessInterceptor apiAccessInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiAccessInterceptor)
                .addPathPatterns("/pokedex/**"); // For all: "/**"
    }
}