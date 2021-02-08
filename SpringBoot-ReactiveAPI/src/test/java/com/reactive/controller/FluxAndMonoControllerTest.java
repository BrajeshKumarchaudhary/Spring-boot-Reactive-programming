package com.reactive.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = MonoAndFluxController.class)
public class FluxAndMonoControllerTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	void testFirstAPIEndPoint() {
		Flux<Integer> fluxInteger = webClient.get().uri("/flux").accept(MediaType.APPLICATION_JSON_UTF8).exchange()
				.expectStatus().isOk().returnResult(Integer.class).getResponseBody();
		StepVerifier.create(fluxInteger).expectSubscription().expectNext(1).expectNext(2).expectNext(3)
				.verifyComplete();
	}
}
