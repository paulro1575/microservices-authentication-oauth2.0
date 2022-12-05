package com.security.micro1.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

//    @Bean
//    @Throws(Exception::class)
//    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
//        http.authorizeRequests(Customizer { authz ->
//            authz.antMatchers(HttpMethod.GET, "/quotes/**")
//                .hasAuthority("SCOPE_read")
//                .antMatchers(HttpMethod.POST, "/quotes")
//                .hasAuthority("SCOPE_read")
//                .anyRequest()
//                .authenticated()
//        }).oauth2ResourceServer { oauth2: OAuth2ResourceServerConfigurer<HttpSecurity> -> oauth2.jwt() }
//        return http.build()
//    }
}