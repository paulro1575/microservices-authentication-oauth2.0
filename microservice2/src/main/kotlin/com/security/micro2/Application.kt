package com.security.micro2

import com.security.micro2.config.KeycloakReactiveTokenInstrospector
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.oauth2.server.resource.introspection.NimbusReactiveOpaqueTokenIntrospector
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector


@SpringBootApplication
@EnableWebFluxSecurity
class Application {

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<Application>(*args)
		}
	}

	@Bean
	fun keycloakIntrospector(props: OAuth2ResourceServerProperties): ReactiveOpaqueTokenIntrospector {
		val delegate = NimbusReactiveOpaqueTokenIntrospector(
			props.opaquetoken.introspectionUri,
			props.opaquetoken.clientId,
			props.opaquetoken.clientSecret
		)
		return KeycloakReactiveTokenInstrospector(delegate)
	}
}
