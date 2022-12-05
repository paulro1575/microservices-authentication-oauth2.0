package com.security.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain



@SpringBootApplication
@EnableConfigurationProperties
class GatewayApplication {

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<GatewayApplication>(*args)
		}
	}

	@Bean
	fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
		return http.httpBasic().and()
			.csrf().disable()
			.authorizeExchange()
			.pathMatchers("/microservice1").authenticated()
            .pathMatchers("/microservice2").authenticated()
            .pathMatchers("/microservice3").authenticated()
			.and().oauth2Login()
			.and()
			.build()
	}
}
