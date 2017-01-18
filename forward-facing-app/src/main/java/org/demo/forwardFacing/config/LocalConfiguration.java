package org.demo.forwardFacing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("local")
public class LocalConfiguration {

	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	
}
