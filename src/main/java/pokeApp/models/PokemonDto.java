package pokeApp.models;

import java.util.Optional;

public class PokemonDto {
	public int id;
	public String name;	
	public int height;
	public  int weight;
	 public String img;
	
	public PokemonDto(PokeApiDto pokeData) {
		this.id = pokeData.id;
		this.name = pokeData.name;		
		this.height = pokeData.height;
		this.weight = pokeData.weight;
		this.img = Optional.ofNullable(pokeData.sprites.other.officialArtwork.frontDefault).orElse(pokeData.sprites.other.dreamWorld.frontDefault) ;
	}
	
}