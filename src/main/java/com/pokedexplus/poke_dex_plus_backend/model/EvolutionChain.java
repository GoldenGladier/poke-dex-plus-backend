package com.pokedexplus.poke_dex_plus_backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = "stages")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvolutionChain {
    @Id
    private Integer id;

//    @OneToMany(mappedBy = "evolutionChain", cascade = CascadeType.ALL)
//    private List<Pokemon> pokemons;

    @OneToMany(mappedBy = "evolutionChain", cascade = CascadeType.ALL)
    @Builder.Default
    private List<EvolutionStage> stages = new ArrayList<>();
}
