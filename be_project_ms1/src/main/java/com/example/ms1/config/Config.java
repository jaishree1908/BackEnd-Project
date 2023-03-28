package com.example.ms1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.example.ms1.dto.ClientHttpRequestInterceptorImpl;

@Configuration
public class Config {

	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate;
	}


	@Bean
	ClientHttpRequestInterceptor getRestTemplateInterceptor( ) {
		return new ClientHttpRequestInterceptorImpl();
	}
}
