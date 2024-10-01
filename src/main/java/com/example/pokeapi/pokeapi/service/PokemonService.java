package com.example.pokeapi.pokeapi.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    private static Logger logger = LoggerFactory.getLogger(PokemonService.class);

    private HttpClient client;

    @Value("${pokeapi.base_url}")
    private String api;

    public PokemonService(HttpClient client) {
        this.client = client;
    }

    public void getPokemonByName(String nombre) {
        StringBuilder str = new StringBuilder();
        str.append(api);
        logger.info(api);
        str.append("&n" + nombre);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(str.toString())).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
