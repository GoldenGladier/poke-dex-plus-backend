package com.pokedexplus.poke_dex_plus_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pokemon {
    @Id
    private Long id;

    @NotNull
    private String name;

    private Integer height;
    private Integer weight;

    @Column(name = "url_image")
    private String urlImage;

    @Column(name = "base_experience")
    private Integer baseExperience;

    @ManyToOne
    @JoinColumn(name = "evolution_chain_id", nullable = false)
    private EvolutionChain evolutionChain;
}
