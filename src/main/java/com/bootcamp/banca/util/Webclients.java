package com.bootcamp.banca.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class Webclients {
	String endpointCustomerE = "http://localhost:8091/";
	String pathGetCustomerE = "e-customer/find?ruc=";

	private final WebClient client;

	public Webclients(WebClient.Builder builder) {
		this.client = builder.baseUrl(endpointCustomerE).build();
	}

	public Mono<Map<String, Object>> getClientEnterprise(String documentNumber) {
		return this.client.get().uri(pathGetCustomerE.concat(documentNumber)).retrieve()
				.onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty()).bodyToMono(Map.class)
				.flatMap(map -> {
					Map<String, Object> response = new HashMap<>();
					response.put("ruc", map.get("ruc"));
					response.put("profile", map.get("profile"));

					return Mono.just(response);
				});
	}
}