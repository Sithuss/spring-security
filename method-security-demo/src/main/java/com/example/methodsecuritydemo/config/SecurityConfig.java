package com.example.methodsecuritydemo.config;

import com.example.methodsecuritydemo.security.DocumentPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private DocumentPermissionEvaluator evaluator;
    @Bean
    public MethodSecurityExpressionHandler expressionHandler() {
        var expressionHandler=new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(evaluator);
        return expressionHandler;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var uds = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("john")
                .password("12345")
                .roles("ADMIN")
                .build();
        var user2 = User.withUsername("emma")
                .password("12345")
                .roles("USER")
                .build();

        uds.createUser(user1);
        uds.createUser(user2);
        return uds;
    }
}
