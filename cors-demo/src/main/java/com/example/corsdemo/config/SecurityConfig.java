package com.example.corsdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        http.cors(c -> {
//            CorsConfigurationSource source =
//                    request -> {
//                        CorsConfiguration config =
//                        new CorsConfiguration();
//                config.setAllowedOrigins(
//                        List.of("example.org", "http://http://127.0.0.1:8080/")
//                );
//                config.setAllowedMethods(
//                        List.of("GET", "POST", "PUT", "DELETE")
//                );
//                return config;
//                    };
//            c.configurationSource(source);
//        });
//
        http.csrf().disable();

        http.authorizeHttpRequests(r -> r.anyRequest().permitAll());

        return http.build();

    }

    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }
}
