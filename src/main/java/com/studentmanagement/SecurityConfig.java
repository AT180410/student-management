package com.studentmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())            // Bỏ CSRF để form POST không bị 403
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()           // Cho phép toàn bộ request
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin()) // Cho phép H2 Console nếu dùng
            );

        return http.build();
    }
}
