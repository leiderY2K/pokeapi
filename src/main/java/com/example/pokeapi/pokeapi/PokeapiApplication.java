package com.example.pokeapi.pokeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.pokeapi.pokeapi.service.PokemonService;

@SpringBootApplication
public class PokeapiApplication implements CommandLineRunner {

	private PokemonService pokemonService;

	public PokeapiApplication(@Autowired PokemonService pokemonService) {
		this.pokemonService = pokemonService;
	}

	public static void main(String[] args) {
		SpringApplication.run(PokeapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		pokemonService.getPokemonByName("pikachu");
	}

}
