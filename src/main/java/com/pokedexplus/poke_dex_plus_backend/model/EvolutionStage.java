package com.pokedexplus.poke_dex_plus_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "evolutionChain")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvolutionStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer stageOrder;

    @ManyToOne
    @JoinColumn(name = "evolution_chain_id", nullable = false)
    @JsonBackReference
    private EvolutionChain evolutionChain;

    @OneToOne
    @JoinColumn(name = "pokemon_id", nullable = false)
    private Pokemon pokemon;
}
