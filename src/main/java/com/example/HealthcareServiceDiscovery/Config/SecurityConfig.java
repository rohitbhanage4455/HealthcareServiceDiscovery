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

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll().requestMatchers("/providers/**").permitAll().requestMatchers("/services/**").permitAll().requestMatchers("/provider-services/**").permitAll().anyRequest().authenticated()).authenticationProvider(authenticationProvider()).formLogin(form -> form.permitAll());

        return http.build();
    }
}