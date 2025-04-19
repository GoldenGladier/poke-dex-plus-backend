package com.pokedexplus.poke_dex_plus_backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL)
    private List<EvolutionChain> evolutionChains;
}
