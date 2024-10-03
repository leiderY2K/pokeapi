package pokeApp.config;

import java.net.http.HttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

	@Bean
	@Scope("prototype")
	HttpClient httpClient() {
		return HttpClient.newHttpClient();
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
