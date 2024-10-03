package pokeApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import pokeApp.models.PokeApiDto;
import pokeApp.models.PokemonDto;

@Service
public class PokemonService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${pokeapi.base_url}")
	private String apiUrl;

	public PokemonDto getPokemonByName(String name) throws HttpClientErrorException {
		try {
			String requestUrl = "%s/%s".formatted(apiUrl, name);
			var pokeApiResponse = restTemplate.getForObject(requestUrl, PokeApiDto.class);
			return new PokemonDto(pokeApiResponse);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND)
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("No se encontró un pokemon llamado '%s'", name));
			else
				throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error desconocido al comunicarse con PokeAPI");
		}
	}
}
