package com.pokedexplus.poke_dex_plus_backend.exception;

public class InvalidPokemonIdException extends RuntimeException {
    public InvalidPokemonIdException(String message) {
        super(message);
    }
}