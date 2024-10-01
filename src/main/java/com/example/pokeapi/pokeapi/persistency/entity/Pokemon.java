package com.example.pokeapi.pokeapi.persistency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Pokemon {
    private int id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String imgpokemon;
}
