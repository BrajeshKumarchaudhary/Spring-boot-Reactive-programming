package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Service
public class AsynchronousServiceCall {

	@Autowired
	WebClient webClient;

	public Flux<Integer> getIntegerAsync(final String id) {
		return webClient.get().uri("/flux").retrieve().bodyToFlux(Integer.class);
	}

	public void printIntegerAsync(final String id) {
		webClient.get().uri("/flux").retrieve().bodyToFlux(Integer.class).subscribe(s -> System.out.println(s));
	}

	//Error Handelling
	
	public void printWithErrorIntegerAsync(final String id) {
		webClient.get().uri("/flux").retrieve().
		bodyToFlux(Integer.class).doOnError(error->System.out.println(error)).
		subscribe(s -> System.out.println(s));
	}
	
	
}
