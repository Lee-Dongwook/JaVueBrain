package com.example.backend.config;

import com.example.backend.security.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
        .requestMatchers("/","/login","/css/**","/js/**","/images/**").permitAll()
        .anyRequest().authenticated()
        )
        .oauth2Login(oauth -> oauth
        .loginPage("/login")
        .userInfoEndpoint(u -> u.userService(new CustomOAuth2UserService()))
        .defaultSuccessUrl("/",true)
        )
        .logout(logout -> logout
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        );
        return http.build();
    }
}
