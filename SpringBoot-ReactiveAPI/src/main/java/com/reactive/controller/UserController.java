package com.reactive.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.model.User;
import com.reactive.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(value = "/getAllUser", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<User> getAllUser() {
		return userService.getAllUser().limitRate(1).log();
	}
	
	@PostMapping(value = "/createUser")
	public Mono<User> createUser() {
		return userService.createUser(new User("12"+new Date().getTime(), "brajesh", "888888888", "Kanpur")).log();
	}

}
