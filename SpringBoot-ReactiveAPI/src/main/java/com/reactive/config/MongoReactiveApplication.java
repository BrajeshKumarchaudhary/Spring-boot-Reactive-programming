package com.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@EnableReactiveMongoRepositories
public class MongoReactiveApplication extends AbstractReactiveMongoConfiguration {

	@Bean
	public MongoClient mongoClient() {
		ConnectionString url = new ConnectionString("mongodb://localhost:27017/user_db");
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(url).build();
		return MongoClients.create(mongoClientSettings);
	}

	@Override
	protected String getDatabaseName() {
		return "user_db";
	}

}
