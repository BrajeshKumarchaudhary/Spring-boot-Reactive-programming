package com.reactive.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.model.User;

@Repository
public interface UserRepo extends ReactiveMongoRepository<User, String> {

}
