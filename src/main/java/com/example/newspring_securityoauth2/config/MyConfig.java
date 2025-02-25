package com.example.newspring_securityoauth2.config;

import com.example.newspring_securityoauth2.service.CustomOauth2Service;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class MyConfig {

    private final CustomOauth2Service service;

    public MyConfig (CustomOauth2Service service){
        this.service = service;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/error", "/webjars/**").permitAll()
                        .requestMatchers("/admin","/get/users").hasRole("ADMIN")
                        .requestMatchers("/user").hasAuthority("USER")
                        .anyRequest().authenticated())
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .oauth2Login(oauth -> oauth
                        .loginPage("/")
                        .userInfoEndpoint(endPoint -> endPoint.userService(service))
                        .defaultSuccessUrl("/user"));
        return http.build();
    }
}
