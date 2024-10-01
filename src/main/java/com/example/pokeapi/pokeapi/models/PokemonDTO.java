package com.example.pokeapi.pokeapi.models;

public record PokemonDTO(
    String nombre, 
    String descripcion, 
    String categoria, 
    String imgPokemon) {

}
