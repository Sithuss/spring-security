package com.example.jdbcuserdetailsmanagerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        String userByNameQuery = """
                select username, password, enabled from users where username = ? 
                """;

        String authsByUserQuery = """
                select username, authority
                from authorities where username= ? 
                """;

        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(userByNameQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
        return userDetailsManager;
//        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
