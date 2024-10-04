package pokeApp.controllers;

import java.util.Collections;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ServerWebExchange;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pokeApp.models.PokemonDto;
import pokeApp.service.PokemonService;

@Controller
public class HomeController {
	@Autowired
	private PokemonService pokeService;

	@Autowired
	private SpringTemplateEngine templateEngine;

	private JakartaServletWebApplication webApp;

	public HomeController(ServletContext servletContext) { this.webApp = JakartaServletWebApplication.buildApplication(servletContext); }

	@GetMapping("/home")
	public String home() { return "home/index"; }

	@GetMapping("/")
	public String redirectRoot() { return "redirect:/home"; }

	@GetMapping("/home/{anyOtherPath}")
	public String redirectOthers() { return "redirect:/home"; }

	@GetMapping("home/pokemon/{name}")
	public ResponseEntity<?> getPokemon(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable String name) throws HttpClientErrorException {
		try {
			model.addAttribute("pokemon", this.pokeService.getPokemonByName(name));
			var webExchange = this.webApp.buildExchange(request, response);
			var pokemonHtml = getPokeCard(webExchange, model);
			return ResponseEntity.ok(Collections.singletonMap("pokemonHtml", pokemonHtml));
		} catch (HttpClientErrorException e) {
			var responseBody = Collections.singletonMap("message", e.getMessage().substring(4));
			return ResponseEntity.status(e.getStatusCode()).body(responseBody);
		} catch (Exception e) {
			var errorMsg = "Error desconocido en el servidor: %s".formatted(e.getMessage());
			var responseBody = Collections.singletonMap("message", errorMsg);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
		}
	}

	public String getPokeCard(IWebExchange webExchange, Model model) {
		var context = new WebContext(webExchange, webExchange.getLocale());
		context.setVariables(model.asMap());
		return templateEngine.process("home/poke-card", context);
	}
}
