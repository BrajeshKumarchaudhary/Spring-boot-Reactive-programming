package com.reactive.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class MonoAndFluxController {

	@GetMapping(value = "/flux")
	public Flux<Integer> returnFlux() {
		return Flux.just(1, 2, 4, 56).log();
	}

	@GetMapping(value = "/fluxStream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Integer> returnFluxStream() {
		return Flux.just(1, 2, 4, 56).delayElements(Duration.ofSeconds(2)).log();
	}

	@SuppressWarnings("deprecation")
	@GetMapping(value = "/fluxInfiniteStream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Long> returnFluxInfiniteStream() {
		Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1)).log();
		return longFlux;
	}
}
