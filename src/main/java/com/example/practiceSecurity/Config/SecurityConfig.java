package com.example.practiceSecurity.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // ✅ Public / Whitelisted Endpoints
    private static final String[] WHITELIST = {
//            "/hello2",
            "/auth/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/health",
            "/actuator/**",
            "/h2-console",
            "/h2-console/**"

};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**") // ⭐
                )

                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable()) // ⭐
                )

                .anonymous(anonymous -> anonymous.disable())  // ⭐ FIX


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )

                // ✅ ENABLE BASIC AUTH
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

