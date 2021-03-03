package com.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactive.model.User;
import com.reactive.repo.UserRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	public Mono<User> createUser(User user) {
		return userRepo.save(user);
	}

	public Flux<User> getAllUser() {
		return userRepo.findAll();
	}
}
