package com.example.HealthcareServiceDiscovery.Config;

import com.example.HealthcareServiceDiscovery.Service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {

        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth

                .requestMatchers("/auth/**").permitAll()

                .requestMatchers(org.springframework.http.HttpMethod.GET, "/providers/**").hasAnyRole("USER", "ADMIN")

                .requestMatchers(org.springframework.http.HttpMethod.POST, "/providers/**").hasRole("ADMIN")

                .requestMatchers(org.springframework.http.HttpMethod.PUT, "/providers/**").hasRole("ADMIN")

                .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/providers/**").hasRole("ADMIN")

                .requestMatchers(org.springframework.http.HttpMethod.GET, "/services/**").hasAnyRole("USER", "ADMIN")

                .requestMatchers(org.springframework.http.HttpMethod.POST, "/services/**").hasRole("ADMIN")

                .requestMatchers(org.springframework.http.HttpMethod.PUT, "/services/**").hasRole("ADMIN")

                .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/services/**").hasRole("ADMIN")

                .requestMatchers(org.springframework.http.HttpMethod.GET, "/provider-services/**").hasAnyRole("USER", "ADMIN")

                .requestMatchers(org.springframework.http.HttpMethod.POST, "/provider-services/**").hasRole("ADMIN")

                .requestMatchers(org.springframework.http.HttpMethod.PUT, "/provider-services/**").hasRole("ADMIN")

                .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/provider-services/**").hasRole("ADMIN")

                .anyRequest().authenticated()).authenticationProvider(authenticationProvider()).formLogin(form -> form.permitAll());

        return http.build();
    }
}