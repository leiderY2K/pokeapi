package pokeApp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokeApiDto {
	@JsonProperty("id")
	public int id;
	
	@JsonProperty("name")
	public String name;

	@JsonProperty("height")
	public int height;

	@JsonProperty("weight")
	public int weight;

	@JsonProperty("sprites")
	public Sprite sprites;

	public static class Sprite {
		@JsonProperty("other")
		public Other other;
	}

	public static class Other {
		@JsonProperty("dream_world")
		public DreamWorld dreamWorld;

		@JsonProperty("official-artwork")
		public OfficialArtwork officialArtwork;
	}

	public static class DreamWorld {
		@JsonProperty("front_default")
		public String frontDefault;

		@JsonProperty("front_female")
		public String frontFemale;
	}

	public static class OfficialArtwork {
		@JsonProperty("front_default")
		public String frontDefault;

		@JsonProperty("front_shiny")
		public String frontShiny;
	}
}
