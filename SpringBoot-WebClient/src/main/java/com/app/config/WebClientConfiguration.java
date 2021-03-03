package com.app.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Configuration
public class WebClientConfiguration {
	private static final String mainUrl = "localhost:8080/";
	private static final int timeOut = 1000;

	@Bean
	WebClient webClientWithTimeOut() {
		final TcpClient client = TcpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeOut)
				.doOnConnected(connection -> {
					connection.addHandlerLast(new ReadTimeoutHandler(timeOut, TimeUnit.MILLISECONDS));
					connection.addHandlerLast(new WriteTimeoutHandler(timeOut, TimeUnit.MILLISECONDS));
				});
		return WebClient.builder().baseUrl(mainUrl)
				.clientConnector(new ReactorClientHttpConnector(HttpClient.from(client))).build();
	}

}
