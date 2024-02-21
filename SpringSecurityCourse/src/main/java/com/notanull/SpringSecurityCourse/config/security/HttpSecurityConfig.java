package com.notanull.SpringSecurityCourse.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    private final AuthenticationProvider daoauthProvider;

    public HttpSecurityConfig(AuthenticationProvider daoauthProvider) {
        this.daoauthProvider = daoauthProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessConfig -> sessConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoauthProvider)
                .authorizeHttpRequests(authReqConfig -> {
                    authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll(); //publico
                    authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll(); //publico
                    authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate").permitAll(); //publico
                    authReqConfig.anyRequest().authenticated(); //Cualquier otro request, autenticado
                })
                .build();
    }
}
