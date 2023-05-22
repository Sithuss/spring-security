package com.example.authorizedemo.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String expression = "hasAuthority('read') and !hasAuthority('delete')";
        String timeExpression = "T(java.time.LocalTime).now().isAfter(T(java.time.LocalTime).of(12,0))";
        http.httpBasic()
                .and()
                //.authorizeHttpRequests(c -> c.anyRequest()
                        //.hasAnyRole("ADMIN", "USER"));
                        //.access(new WebExpressionAuthorizationManager(timeExpression));
                .authorizeHttpRequests()
                .requestMatchers("/hello").hasRole("USER")
                .requestMatchers("/greeting").hasRole("ADMIN")
                .requestMatchers("/products/*/{code:^[0-9-]*$}")
                .permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        var uds = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("john")
                .password(passwordEncoder().encode("12345"))
                .roles("USER", "ADMIN")
                .build();

        var user2 = User.withUsername("mary")
                .password(passwordEncoder().encode("12345"))
                .roles("USER")
                .build();
        uds.createUser(user1);
        uds.createUser(user2);
        return uds;
    }
}
