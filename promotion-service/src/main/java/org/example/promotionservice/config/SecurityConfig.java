package org.example.promotionservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Bạn có thể đổi thành .permitAll() nếu muốn công khai hoàn toàn endpoint /products
                        .anyRequest().permitAll()
                )

                // 3. Kích hoạt phương thức đăng nhập cơ bản Basic Auth
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
