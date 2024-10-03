package pokeApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;

import pokeApp.service.PokemonService;

@Controller
public class HomeController {
	@Autowired
	private PokemonService pokeService;

	@GetMapping("/home")
	public String home() { return "home/index"; }
	
	@GetMapping("/")
	public String redirectRoot() { return "redirect:/home"; }
	
	@GetMapping("/home/{anyOtherPath}")
	public String redirectOthers() { return "redirect:/home"; }

	@GetMapping("home/pokemon/{name}")
	public String getPokemon(@PathVariable String name, Model model) throws HttpClientErrorException {
		model.addAttribute("pokemon", this.pokeService.getPokemonByName(name));
		return "home/poke-card";
	}
}
