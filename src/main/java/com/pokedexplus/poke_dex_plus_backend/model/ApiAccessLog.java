package com.pokedexplus.poke_dex_plus_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiAccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación opcional con User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    // Relación opcional con Pokemon
    @ManyToOne
    @JoinColumn(name = "pokemon_id", nullable = true)
    private Pokemon pokemon;

    @NotNull
    private String endpointAccessed;

    @NotNull
    private LocalDateTime accessTime;

    @NotNull
    private int statusCode;

    @NotNull
    private String action; // Ej: "GET", "POST"

    @PrePersist
    protected void onCreate() {
        accessTime = LocalDateTime.now();
    }
}