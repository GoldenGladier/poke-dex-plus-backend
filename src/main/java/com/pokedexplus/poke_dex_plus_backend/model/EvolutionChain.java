package com.pokedexplus.poke_dex_plus_backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class EvolutionChain {
    @Id
    private Integer id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "pokemon_id", nullable = false)
    private Pokemon pokemon;

    @OneToMany(mappedBy = "evolutionChain", cascade = CascadeType.ALL)
    private List<EvolutionStage> stages;
}
