package com.example.pokeapi.pokeapi.config;

import java.net.http.HttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class WebConfig {

    @Bean
    @Scope("prototype")
    public HttpClient getHttpClient(){
        return HttpClient.newHttpClient();
    }

}
